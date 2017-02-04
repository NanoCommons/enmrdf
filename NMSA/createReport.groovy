nmsaData = rdf.createInMemoryStore()
rdf.importFile(nmsaData, "/NMSA/data.ttl", "TURTLE")
rdf.importFile(nmsaData, "/NMSA/moredata.ttl", "TURTLE")

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

def outputAbstractInfo(allAbstractMatches, rowMatch) {
  matchID = allAbstractMatches.get(rowMatch, "id")
  if (!matchID.equals(abstrID)) {
    matchIRI = allAbstractMatches.get(rowMatch, "abstract")
    if (allAbstractMatches.get(rowMatch, "session").contains("Poster")) {
      aReport
        .addText("<span width=\"40\" height=\"40\" style=\"background-color: navy; color: white\"><i>&nbsp;P&nbsp;</i></span> ")
    } else {
      aReport
        .addText("<span width=\"40\" height=\"40\" style=\"background-color: darkred; color: white\"><i>&nbsp;O&nbsp;</i></span> ")
    }
    aReport.addText("#")
      .addLink("./abstract${matchID}.html", matchID)
    matchTitle = allAbstractMatches.get(rowMatch, "title")
    if (matchTitle.length() > 50) {
      aReport.addText(": ").addText(matchTitle.substring(0,47), "ITALIC").addText("...")
    } else {
      aReport.addText(": ").addText(matchTitle, "ITALIC").addText("...")
    }
    aReport
      .addText(" ").addText(allAbstractMatches.get(rowMatch, "session"))
      .addText(" ").addText(allAbstractMatches.get(rowMatch, "day"))
      .addText(" (").addText(allAbstractMatches.get(rowMatch, "start"))
      .addText("-").addText(allAbstractMatches.get(rowMatch, "end"))
      .addText(")")
    guideID = allAbstractMatches.get(rowMatch, "guide")
    if (guideID != null && guideID.trim().length() > 0) {
      aReport.addText(" ")
      guideID = guideID.trim()
      if (allAbstractMatches.get(rowMatch, "session").contains("Poster")) {
        aReport.addLink("https://guidebook.com/guide/86999/poi/$guideID/", "Guidebook")
      } else {
        aReport.addLink("https://guidebook.com/guide/86999/event/$guideID/", "Guidebook")
      }
    }
    aReport
      .forceNewLine()
  }
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
SELECT ?abstract ?id ?title ?session ?start ?end WHERE {
  ?abstract a <http://edamontology.org/data_2849> ;
    <http://purl.org/dc/terms/identifier> ?id ;
    <http://purl.org/dc/terms/title> ?title ;
    <http://example.org/NMSA17/onto/session> ?session ;
    <http://example.org/NMSA17/onto/startTime> ?start ;
    <http://example.org/NMSA17/onto/endTime> ?end .
  OPTIONAL { ?abstract <http://example.org/NMSA17/onto/guidebookID> ?guide . }
}
"""

abstracts = rdf.sparql(nmsaData, allAbstractsQuery)

for (row=1; row<=abstracts.rowCount; row++) {
  abstrID = abstracts.get(row, "id")
  abstrIRI = abstracts.get(row, "abstract")
  outputFile = "/NMSA/reports/abstract${abstrID}.html"
  if (ui.fileExists(outputFile)) ui.remove(outputFile)

  indexReport = report.createReport()
    .addText("<img height=\"200\" src=\"http://www.nmsaconference.eu/_img/cabecera/!\" />")
    .createHeader("","Index of Annotated Abstract")
    .forceNewLine()
  aReport = report.createReport()
    .addText("<img height=\"200\" src=\"http://www.nmsaconference.eu/_img/cabecera/!\" />")
    .createHeader("","Recommendations based on Abstract $abstrID")
    .startSection("Recommendations based on the annotation of abstract $abstrID")
    .addText(abstracts.get(row, "title"))
    .forceNewLine()
  if (abstracts.get(row, "session").contains("Poster")) {
    aReport
      .addText("<span width=\"40\" height=\"40\" style=\"background-color: navy; color: white\"><i>&nbsp;P&nbsp;</i></span> ")
  } else {
    aReport
      .addText("<span width=\"40\" height=\"40\" style=\"background-color: darkred; color: white\"><i>&nbsp;O&nbsp;</i></span> ")
  }
  aReport
    .addText(
      abstracts.get(row, "session") + " - " +
      abstracts.get(row, "start") + "-" +
      abstracts.get(row, "end")
    )
    .forceNewLine()
    .forceNewLine()

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
          .addLink("https://search.data.enanomapper.net/?search=" + shortened, "search via eNanoMapper")
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

  allCellsQuery = """
    SELECT ?cellline WHERE {
      <$abstrIRI> <http://example.org/NMSA17/onto/withCellLine> ?cellline
    }
  """
  allLines = rdf.sparql(nmsaData, allCellsQuery)
  if (allLines.rowCount > 0) {
    aReport.addText("Cell lines: ", "BOLD")
    for (cellline in allLines.getColumn("cellline")) {
      label = shorten(cellline)
      if (label == null || label.trim().length() == 0) {
        aReport.addLink(cellline, shorten(cellline))
      } else {
        aReport.addLink("http://bioportal.bioontology.org/ontologies/ENM/?p=classes&jump_to_nav=true&conceptid=" + cellline.encodeURL(), shorten(cellline))
      }
    }
    aReport.forceNewLine()
  }

  aReport
    .startSubSection("Recommended abstracts")

  aReport
    .startSubSubSection("Same material")
  allMaterialsQuery = """
    SELECT ?enm WHERE {
      <$abstrIRI> <http://example.org/NMSA17/onto/aboutMaterial> ?enm
    }
  """
  allMaterials = rdf.sparql(nmsaData, allMaterialsQuery)
  if (allMaterials.rowCount > 0) {
    for (enm in allMaterials.getColumn("enm")) {
      label = owlapi.getLabel(ontologyObj, enm)
      if (label == null || label.trim().length() == 0) label = shorten(enm)
      allAbstractsForMaterialQuery = """
        SELECT ?abstract ?id ?title ?day ?session ?start ?end ?guide WHERE {
          ?abstract <http://example.org/NMSA17/onto/aboutMaterial> <$enm> ;
            <http://purl.org/dc/terms/identifier> ?id ;
            <http://purl.org/dc/terms/title> ?title ;
            <http://example.org/NMSA17/onto/session> ?session ;
            <http://example.org/NMSA17/onto/day> ?day ;
            <http://example.org/NMSA17/onto/startTime> ?start ;
            <http://example.org/NMSA17/onto/endTime> ?end .
          OPTIONAL { ?abstract <http://example.org/NMSA17/onto/guidebookID> ?guide . }
        } ORDER BY ASC(?id)
      """
      allAbstractMatches = rdf.sparql(nmsaData, allAbstractsForMaterialQuery)
      if (allAbstractMatches.rowCount > 1) {
        aReport.addText("Other abstracts about ")
          .addText(label, "BOLD").forceNewLine()
          .startIndent()
        for (rowMatch=1; rowMatch<=allAbstractMatches.rowCount; rowMatch++) {
          outputAbstractInfo(allAbstractMatches, rowMatch)
        }
        aReport.endIndent()
      }
    }
    aReport.forceNewLine()
  }

  aReport
    .startSubSubSection("Same species")
  allSpeciesQuery = """
    SELECT ?species WHERE {
      <$abstrIRI> <http://example.org/NMSA17/onto/aboutSpecies> ?species
    }
  """
  allSpecies = rdf.sparql(nmsaData, allSpeciesQuery)
  if (allSpecies.rowCount > 0) {
    for (species in allSpecies.getColumn("species")) {
      label = owlapi.getLabel(ontologyObj, species)
      if (label == null || label.trim().length() == 0) label = shorten(species)
      allAbstractsForSpeciesQuery = """
        SELECT ?abstract ?id ?title ?day ?session ?start ?end ?guide WHERE {
          ?abstract <http://example.org/NMSA17/onto/aboutSpecies> <$species> ;
            <http://purl.org/dc/terms/identifier> ?id ;
            <http://purl.org/dc/terms/title> ?title ;
            <http://example.org/NMSA17/onto/session> ?session ;
            <http://example.org/NMSA17/onto/day> ?day ;
            <http://example.org/NMSA17/onto/startTime> ?start ;
            <http://example.org/NMSA17/onto/endTime> ?end .
          OPTIONAL { ?abstract <http://example.org/NMSA17/onto/guidebookID> ?guide . }
        } ORDER BY ASC(?id)
      """
      allAbstractMatches = rdf.sparql(nmsaData, allAbstractsForSpeciesQuery)
      if (allAbstractMatches.rowCount > 1) {
        aReport.addText("Other abstracts about ")
          .addText(labelSpecies(label), "BOLD").forceNewLine()
          .startIndent()
        for (rowMatch=1; rowMatch<=allAbstractMatches.rowCount; rowMatch++) {
          outputAbstractInfo(allAbstractMatches, rowMatch)
        }
        aReport.endIndent()
      }
    }
    aReport.forceNewLine()
  }

  html = report.asHTML(aReport)
  ui.append(outputFile, html)
}

html = report.asHTML(indexReport)
ui.append(outputFile = "/NMSA/reports/index.html", html)
