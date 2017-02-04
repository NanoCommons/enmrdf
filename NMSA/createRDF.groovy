outputFile = "/NMSA/data.ttl"
doiOutputFile = "/NMSA/doi.ttl"
orcidOutputFile = "/NMSA/orcid.ttl"

if (ui.fileExists(outputFile)) ui.remove(outputFile)
if (ui.fileExists(doiOutputFile)) ui.remove(doiOutputFile)

processedDOIs = new ArrayList();
doiData = rdf.createInMemoryStore()
rdf.addPrefix(doiData, "foaf", "http://xmlns.com/foaf/0.1/")
rdf.addPrefix(doiData, "author", "http://id.crossref.org/contributor/")
rdf.addPrefix(doiData, "prism", "http://prismstandard.org/namespaces/basic/2.1/")
rdf.addPrefix(doiData, "bibo", "http://purl.org/ontology/bibo/")
rdf.addPrefix(doiData, "dcterms", "http://purl.org/dc/terms/")

processedORCIDs = new ArrayList();
orcidData = rdf.createInMemoryStore()


rdfData = rdf.createInMemoryStore()
rdf.addPrefix(rdfData, "void", "http://rdfs.org/ns/void#")
rdf.addPrefix(rdfData, "rdf", "http://www.w3.org/1999/02/22-rdf-syntax-ns#")
rdf.addPrefix(rdfData, "dcterms", "http://purl.org/dc/terms/") 
rdf.addPrefix(rdfData, "obo", "http://purl.obolibrary.org/obo/")
rdf.addPrefix(rdfData, "rdfs", "http://www.w3.org/2000/01/rdf-schema#")
rdf.addPrefix(rdfData, "npo", "http://purl.bioontology.org/ontology/npo#")
rdf.addPrefix(rdfData, "sso", "http://semanticscience.org/resource/")
rdf.addPrefix(rdfData, "ex", "http://example.org/")
rdf.addPrefix(rdfData, "bao", "http://www.bioassayontology.org/bao#")
rdf.addPrefix(rdfData, "cito", "http://purl.org/net/cito/")
rdf.addPrefix(rdfData, "enm", "http://purl.enanomapper.org/onto/")
rdf.addPrefix(rdfData, "foaf", "http://xmlns.com/foaf/0.1/")
rdf.addPrefix(rdfData, "efo", "http://www.ebi.ac.uk/efo/")

rdf.addPrefix(rdfData, "nmsa", "http://example.org/NMSA17/onto/")
rdf.addPrefix(rdfData, "abstract", "http://example.org/NMSA17/abstract/")
rdf.addPrefix(rdfData, "orcid", "http://orcid.org/")
rdf.addPrefix(rdfData, "taxon", "http://purl.org/obo/owl/NCBITaxon#NCBITaxon_")

// tricks for types
rdf.addPrefix(rdfData, "Abstract", "http://edamontology.org/data_2849")
rdf.addPrefix(rdfData, "isAbout", "http://purl.bioontology.org/ontology/SNOMEDCT/704647008")


datasetStr = "http://example.org/NMSA17" 
rdf.addObjectProperty(rdfData,
  datasetStr,
  "http://www.w3.org/1999/02/22-rdf-syntax-ns#type",
  "http://rdfs.org/ns/void#Dataset"
)
rdf.addDataProperty(rdfData,
  datasetStr, "http://purl.org/dc/terms/title",
  "New tools and approaches for nanomaterial safety assessment"
)
rdf.addDataProperty(rdfData,
  datasetStr, "http://xmlns.com/foaf/0.1/",
  "http://xmlns.com/foaf/0.1/"
)

speciesMappings = new HashMap()
  speciesMappings.put("Escherichia coli",          "http://purl.obolibrary.org/obo/NCBITaxon_562")
  speciesMappings.put("(7955)",                    "http://purl.obolibrary.org/obo/NCBITaxon_7955") 
  speciesMappings.put("Cyprinus carpio",           "http://purl.obolibrary.org/obo/NCBITaxon_7962") 
  speciesMappings.put("Oncorhynchus mykiss",       "http://purl.obolibrary.org/obo/NCBITaxon_8022")
    speciesMappings.put("rainbow trout",           "http://purl.obolibrary.org/obo/NCBITaxon_8022")
  speciesMappings.put("(9606)",                    "http://purl.obolibrary.org/obo/NCBITaxon_9606") 
  speciesMappings.put("(10090)",                   "http://purl.obolibrary.org/obo/NCBITaxon_10090") 
  speciesMappings.put("(10116)",                   "http://purl.obolibrary.org/obo/NCBITaxon_10116")
    speciesMappings.put("rat ",                    "http://purl.obolibrary.org/obo/NCBITaxon_10116")
  speciesMappings.put("Daphnia magna",             "http://purl.obolibrary.org/obo/NCBITaxon_35525")

enmMappings = new HashMap()
  enmMappings.put("multiwalled carbon nanotube",   "http://purl.bioontology.org/ontology/npo#NPO_354")
    enmMappings.put("MWCNT",                       "http://purl.bioontology.org/ontology/npo#NPO_354")
  enmMappings.put("NPO_401",                       "http://purl.bioontology.org/ontology/npo#NPO_401")
    enmMappings.put("gold nanoparticle",           "http://purl.bioontology.org/ontology/npo#NPO_401")
  enmMappings.put("quantum dot",                   "http://purl.bioontology.org/ontology/npo#NPO_589")
    enmMappings.put("CdTe QDs",                    "http://purl.bioontology.org/ontology/npo#NPO_589")
  enmMappings.put("PAMAM",                         "http://purl.bioontology.org/ontology/npo#NPO_965")
  enmMappings.put("NPO_1375",                      "http://purl.bioontology.org/ontology/npo#NPO_1375")
  enmMappings.put("copper oxide",                  "http://purl.bioontology.org/ontology/npo#NPO_1544")
    enmMappings.put("nano-CuO",                    "http://purl.bioontology.org/ontology/npo#NPO_1544")
  enmMappings.put("NPO_1892",                      "http://purl.bioontology.org/ontology/npo#NPO_1892")
  enmMappings.put("CHEBI_33416",                   "http://purl.obolibrary.org/obo/CHEBI_33416")
  enmMappings.put("silicon dioxide nanoparticle",  "http://purl.obolibrary.org/obo/CHEBI_50828")
    enmMappings.put("silicon oxide nanoparticle",  "http://purl.obolibrary.org/obo/CHEBI_50828")
  enmMappings.put("titanium dioxide nanoparticle", "http://purl.obolibrary.org/obo/CHEBI_51050")
    enmMappings.put("NPO_1486",                    "http://purl.obolibrary.org/obo/CHEBI_51050")
  enmMappings.put("copper (II) oxide",             "http://purl.obolibrary.org/obo/CHEBI_83159")
  enmMappings.put("graphene oxide",                "http://purl.obolibrary.org/obo/CHEBI_132889")
  enmMappings.put("polystyrene",                   "http://purl.obolibrary.org/obo/CHEBI_134403")
    enmMappings.put("polysterene",                 "http://purl.obolibrary.org/obo/CHEBI_134403")
  enmMappings.put("graphene nanoparticle",         "http://purl.obolibrary.org/obo/CHEBI_134404")
    enmMappings.put("graphene nanoplatelets",      "http://purl.obolibrary.org/obo/CHEBI_134404")
  enmMappings.put("cerium oxide",                  "http://purl.enanomapper.org/onto/ENM_9000006")
  enmMappings.put("ENM_9000074",                   "http://purl.enanomapper.org/onto/ENM_9000074")

clMappings = new HashMap()
  clMappings.put("A549",                    "http://purl.obolibrary.org/obo/BTO_0000018")
  clMappings.put("RTL-W1",                  "http://purl.obolibrary.org/obo/BTO_0000224")
  clMappings.put("HMC-1",                   "http://purl.obolibrary.org/obo/BTO_0001669")
  clMappings.put("EFO_0003064",             "http://purl.obolibrary.org/obo/BTO_0001845")
  clMappings.put("plhc-1",                  "http://purl.obolibrary.org/obo/BTO_0002331")
  clMappings.put("EAhy926",                 "http://purl.obolibrary.org/obo/BTO_0002602")
  clMappings.put("human blood derived dendritic cells",
                                            "http://www.ebi.ac.uk/efo/EFO_0000396")
  clMappings.put("BEAS-2B",                 "http://www.ebi.ac.uk/efo/EFO_0001089")
  clMappings.put("Caco-2",                  "http://www.ebi.ac.uk/efo/EFO_0001099")
  clMappings.put("HepaRG",                  "http://www.ebi.ac.uk/efo/EFO_0001186")
  clMappings.put("THP-1",                   "http://www.ebi.ac.uk/efo/EFO_0001253")
  clMappings.put("Jurkat",                  "http://www.ebi.ac.uk/efo/EFO_0002796")
  clMappings.put("EFO_0003064",             "http://www.ebi.ac.uk/efo/EFO_0003064")
  clMappings.put("CLC",                     "http://purl.obolibrary.org/obo/CL_0000738")
  clMappings.put("rth-149",                 "http://purl.obolibrary.org/obo/CL_0002618")

dataFile = new File(bioclipse.fullPath("/NMSA/responses.tsv"))
dataFile.eachLine { line, number ->
  if (number < 2) return;
  dataRow = line.split(/\t/)
  abstractNo = dataRow[1]
  try { Integer.valueOf(abstractNo) } catch (Exception e) {
    println "Incorrect abstract number on line $number: $abstractNo"
    return
  }
  abstractRes = datasetStr + "/abstract/a" + abstractNo
  rdf.addDataProperty(rdfData,
    abstractRes,
    "http://purl.org/dc/terms/identifier",
    abstractNo
  )
  try {
    rdf.addObjectProperty(rdfData,
      abstractRes,
      "http://www.w3.org/1999/02/22-rdf-syntax-ns#type",
      "http://edamontology.org/data_2849"
    )
    rdf.addObjectProperty(rdfData,
      abstractRes,
      "http://purl.org/dc/terms/source",
      datasetStr
    )
    
    // ORCIDs
    orcids = dataRow[2]
    if (orcids != null && !orcids.trim().isEmpty()) {
      if (orcids.contains(",")) { // multiple authors
        orcidList = orcids.split(/,/)
        for (orcid in orcidList) {
          orcid = orcid.trim()
          orcidURL = "http://orcid.org/" + orcid.trim()
          if (!processedORCIDs.contains(orcid)) {
            processedORCIDs.add(orcid);
//            println ("ORCID download: " + orcidURL)
//            try { 
//              orcidContent = bioclipse.download(orcidURL, "text/turtle")
//              rdf.importFromString(orcidData, orcidContent, "TURTLE")
//            } catch (Exception x) {}
          }
          rdf.addObjectProperty(rdfData,
            abstractRes,
            "http://purl.org/dc/terms/creator",
            orcidURL
          )
        }
      } else if (orcids.contains(";")) { // multiple authors
        orcidList = orcids.split(/;/)
        for (orcid in orcidList) {
          orcid = orcid.trim()
          orcidURL = "http://orcid.org/" + orcid.trim()
          if (!processedORCIDs.contains(orcid)) {
            processedORCIDs.add(orcid);
//            println ("ORCID download: " + orcidURL)
//            try { 
//              orcidContent = bioclipse.download(orcidURL, "text/turtle")
//              rdf.importFromString(orcidData, orcidContent, "TURTLE")
//            } catch (Exception x) {}
          }
          rdf.addObjectProperty(rdfData,
            abstractRes,
            "http://purl.org/dc/terms/creator",
            orcidURL
          )
        }
      } else if (orcids.contains(" ")) { // multiple authors
        orcidList = orcids.split(/ /)
        for (orcid in orcidList) {
          orcid = orcid.trim()
          orcidURL = "http://orcid.org/" + orcid.trim()
          if (!processedORCIDs.contains(orcid)) {
            processedORCIDs.add(orcid);
//            println ("ORCID download: " + orcidURL)
//            try { 
//              orcidContent = bioclipse.download(orcidURL, "text/turtle")
//              rdf.importFromString(orcidData, orcidContent, "TURTLE")
//            } catch (Exception x) {}
          }
          rdf.addObjectProperty(rdfData,
            abstractRes,
            "http://purl.org/dc/terms/creator",
            orcidURL
          )
        }
      } else { // single author
        orcid = orcids.trim()
        orcidURL = "http://orcid.org/" + orcid.trim()
        if (!processedORCIDs.contains(orcid)) {
          processedORCIDs.add(orcid);
//          println ("ORCID download: " + orcidURL)
//          try { 
//            orcidContent = bioclipse.download(orcidURL, "text/turtle")
//            rdf.importFromString(orcidData, orcidContent, "TURTLE")
//          } catch (Exception x) {}
        }
        rdf.addObjectProperty(rdfData,
          abstractRes,
          "http://purl.org/dc/terms/creator",
          orcidURL
        )
      }
    }

    // species
    species = dataRow[4]
    if (species != null && !species.trim().isEmpty()) {
      for (searchTerm in speciesMappings.keySet()) {
        if (species.toLowerCase().contains(searchTerm.toLowerCase())) {
          speciesRes = speciesMappings.get(searchTerm)
          rdf.addObjectProperty(rdfData,
            abstractRes,
            "http://example.org/NMSA17/onto/aboutSpecies",
            speciesRes
          )
        }
      }
    }

    // materials
    materials = dataRow[3]
    if (materials != null && !materials.trim().isEmpty()) {
      for (searchTerm in enmMappings.keySet()) {
        if (materials.toLowerCase().contains(searchTerm.toLowerCase())) {
          materialsRes = enmMappings.get(searchTerm)
          rdf.addObjectProperty(rdfData,
            abstractRes,
            "http://example.org/NMSA17/onto/aboutMaterial",
            materialsRes
          )
        }
      }
    }

    // cell lines
    cells = dataRow[5]
    if (cells != null && !cells.trim().isEmpty()) {
      for (searchTerm in clMappings.keySet()) {
        if (cells.contains(searchTerm)) {
          celllineRes = clMappings.get(searchTerm)
          rdf.addObjectProperty(rdfData,
            abstractRes,
            "http://example.org/NMSA17/onto/withCellLine",
            celllineRes
          )
        }
      }
    }
  } catch (Exception exc) {
    println "Something wrong on line $number: " + exc.message + ", " + exc
    
  }
}

ui.append(outputFile, rdf.asTurtle(rdfData))
ui.append(doiOutputFile, rdf.asTurtle(doiData))
ui.append(orcidOutputFile, rdf.asTurtle(orcidData))

ui.open(outputFile)
