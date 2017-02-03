nmsaData = rdf.createInMemoryStore()
rdf.importFile(nmsaData, "/NMSA/data.ttl", "TURTLE")

String.metaClass.encodeURL = {
   java.net.URLEncoder.encode(delegate, "UTF-8")
}

def labelSpecies(taxon) {
  label = taxon
  taxon = taxon.replace("7955",  "Danio rerio")
  taxon = taxon.replace("7962",  "Cyprinus carpio")
  taxon = taxon.replace("8022",  "Oncorhynchus mykiss")
  taxon = taxon.replace("9606",  "Homo sapiens")
  taxon = taxon.replace("10090", "Mus musculus")
  taxon = taxon.replace("10116", "Rattus norvegicus")
  taxon = taxon.replace("35525", "Daphnia magna")
  return taxon
}

def shorten(iri) {
  iri = iri.replace("http://orcid.org/", "")
  iri = iri.replace("http://purl.obolibrary.org/obo/NCBITaxon_", "")
  iri = iri.replace("http://purl.obolibrary.org/obo/", "")
  iri = iri.replace("http://www.ebi.ac.uk/efo/", "")
  iri = iri.replace("http://purl.enanomapper.org/onto/", "")
  iri = iri.replace("http://purl.bioontology.org/ontology/npo#", "")
  return iri
}

mapper = null; // initially no mapper

def addImport(mapper, ontologyURI, ontologyFile) {
  localFile = "/eNanoMapper/" + ontologyFile;
  mapper = owlapi.addMapping(mapper, ontologyURI, localFile);
  if (!ui.fileExists(localFile)) {
    // ui.beginTask("Downloading a local copy of " + ontologyFile + "...")
    bioclipse.downloadAsFile(ontologyURI, localFile);
  }
  return mapper;
}

mapper = addImport(mapper, "http://purl.enanomapper.org/onto/enanomapper-auto-dev.owl", "enanomapper-auto.owl");
addImport(mapper, "http://purl.enanomapper.org/onto/internal/endpoints.owl", "endpoints.owl");
addImport(mapper, "http://purl.enanomapper.org/onto/external/ontology-metadata-slim.owl", "ontology-metadata-slim.owl");
addImport(mapper, "http://purl.enanomapper.org/onto/internal/npo-ext.owl", "npo-ext.owl");
addImport(mapper, "http://purl.enanomapper.net/onto-dev/BAO/ws/bao-slim.owl", "bao-slim.owl");
addImport(mapper, "http://purl.enanomapper.net/onto-dev/BFO/ws/bfo-slim.owl", "bfo-slim.owl");
addImport(mapper, "http://purl.enanomapper.net/onto-dev/CCONT/ws/ccont-slim.owl", "ccont-slim.owl");
addImport(mapper, "http://purl.enanomapper.net/onto-dev/CHEBI/ws/chebi-slim.owl", "chebi-slim.owl");
addImport(mapper, "http://purl.enanomapper.net/onto-dev/CHEMINF/ws/cheminf-slim.owl", "cheminf-slim.owl");
addImport(mapper, "http://purl.enanomapper.net/onto-dev/CHMO/ws/chmo-slim.owl", "chmo-slim.owl");
addImport(mapper, "http://purl.enanomapper.net/onto-dev/EFO/ws/efo-slim.owl", "efo-slim.owl");
addImport(mapper, "http://purl.enanomapper.net/onto-dev/ENVO/ws/envo-slim.owl", "envo-slim.owl");
addImport(mapper, "http://purl.enanomapper.net/onto-dev/IAO/ws/iao-slim.owl", "iao-slim.owl");
addImport(mapper, "http://purl.enanomapper.net/onto-dev/NPO/ws/npo-slim.owl", "npo-slim.owl");
addImport(mapper, "http://purl.enanomapper.net/onto-dev/OAE/ws/oae-slim.owl", "oae-slim.owl");
addImport(mapper, "http://purl.enanomapper.net/onto-dev/OBCS/ws/obcs-slim.owl", "obcs-slim.owl");
addImport(mapper, "http://purl.enanomapper.net/onto-dev/OBI/ws/obi-slim.owl", "obi-slim.owl");
addImport(mapper, "http://purl.enanomapper.net/onto-dev/PATO/ws/pato-slim.owl", "pato-slim.owl");
addImport(mapper, "http://purl.enanomapper.net/onto-dev/UO/ws/uo-slim.owl", "uo-slim.owl");
addImport(mapper, "http://protege.stanford.edu/plugins/owl/dc/protege-dc.owl", "protege-dc.owl");
// owlapi.listMappings(mapper)

// OK, now we can load the ontology safely
// js.clear(); // so that I know I'm reading the latest error
ontologyObj = owlapi.load("/eNanoMapper/enanomapper.owl", mapper);


// Now do the real reporting

allAbstractsQuery = """
SELECT ?abstract ?id WHERE {
  ?abstract a <http://edamontology.org/data_2849> ;
    <http://purl.org/dc/terms/identifier> ?id .
}
"""

abstracts = rdf.sparql(nmsaData, allAbstractsQuery)

for (row=1; row<=abstracts.rowCount; row++) {
  abstrID = abstracts.get(row, "id")
  abstrIRI = abstracts.get(row, "abstract")
  outputFile = "/NMSA/reports/abstract${abstrID}.html"
  if (ui.fileExists(outputFile)) ui.remove(outputFile)

  aReport = report.createReport()
    .addText("<img height=\"200\" src=\"http://www.nmsaconference.eu/_img/cabecera/!\" />")
    .createHeader("","Recommendations based on Abstract $abstrID")
    .startSection("Recommendations based on the annotation of abstract $abstrID")

  aReport
    .startSubSection("Details")

  allAuthorsQuery = """
    SELECT ?author WHERE {
      <$abstrIRI> <http://purl.org/dc/terms/creator> ?author
    }
  """
  allAuthors = rdf.sparql(nmsaData, allAuthorsQuery)
  if (allAuthors.rowCount > 0) {
    aReport.addText("Authors: ", "BOLD")
    for (author in allAuthors.getColumn("author")) {
      aReport.addText("<img src=\"https://orcid.org/sites/default/files/images/orcid_16x16.png\" />&nbsp;")
        .addLink(author, shorten(author)).addText(" ")
    }
    aReport.forceNewLine()
  }

  allMaterialsQuery = """
    SELECT ?enm WHERE {
      <$abstrIRI> <http://example.org/NMSA17/onto/aboutMaterial> ?enm
    }
  """
  allMaterials = rdf.sparql(nmsaData, allMaterialsQuery)
  if (allMaterials.rowCount > 0) {
    aReport.addText("Materials: ", "BOLD")
    for (enm in allMaterials.getColumn("enm")) {
      label = owlapi.getLabel(ontologyObj, enm)
      if (label == null || label.trim().length() == 0) label = shorten(enm)
      aReport.addText(label + " (")
        .addLink("http://bioportal.bioontology.org/ontologies/ENM/?p=classes&jump_to_nav=true&conceptid=" + enm.encodeURL(), shorten(enm))
      shortened = shorten(enm)
      if (!shortened.contains("http"))
        aReport.addText(", ")
          .addLink("https://search.data.enanomapper.net/?search=" + shortened, "search online")
      aReport.addText(") ")
    }
    aReport.forceNewLine()
  }

  allSpeciesQuery = """
    SELECT ?species WHERE {
      <$abstrIRI> <http://example.org/NMSA17/onto/aboutSpecies> ?species
    }
  """
  allSpecies = rdf.sparql(nmsaData, allSpeciesQuery)
  if (allSpecies.rowCount > 0) {
    aReport.addText("Species: ", "BOLD")
    for (species in allSpecies.getColumn("species")) {
      label = labelSpecies(shorten(species))
      if (label == null || label.trim().length() == 0) {
        aReport.addLink(species, labelSpecies(shorten(species)))
      } else {
        aReport.addLink("http://www.ontobee.org/ontology/NCBITaxon?iri=" + species, labelSpecies(shorten(species)))
      }
    }
    aReport.forceNewLine()
  }

  aReport
    .startSubSection("Interesting abstracts")

  html = report.asHTML(aReport)
  ui.append(outputFile, html)
}

