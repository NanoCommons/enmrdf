outputFile = "/2nd NanoSafety Forum for Young Scientists/data.ttl"
doiOutputFile = "/2nd NanoSafety Forum for Young Scientists/doi.ttl"

if (ui.fileExists(outputFile)) ui.remove(outputFile)
if (ui.fileExists(doiOutputFile)) ui.remove(doiOutputFile)

processedDOIs = new ArrayList();
doiData = rdf.createInMemoryStore()
rdf.addPrefix(doiData, "foaf", "http://xmlns.com/foaf/0.1/")
rdf.addPrefix(doiData, "author", "http://id.crossref.org/contributor/")
rdf.addPrefix(doiData, "prism", "http://prismstandard.org/namespaces/basic/2.1/")
rdf.addPrefix(doiData, "bibo", "http://purl.org/ontology/bibo/")
rdf.addPrefix(doiData, "dcterms", "http://purl.org/dc/terms/")

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

datasetStr = "http://example.org/NFYS16" 
rdf.addObjectProperty(rdfData,
  datasetStr,
  "http://www.w3.org/1999/02/22-rdf-syntax-ns#type",
  "http://rdfs.org/ns/void#Dataset"
)
rdf.addDataProperty(rdfData,
  datasetStr,
  "http://purl.org/dc/terms/title",
  "2nd NanoSafety Forum for Young Scientists Data"
)

dataFile = new File(bioclipse.fullPath("/2nd NanoSafety Forum for Young Scientists/data.tsv"))
dataFile.eachLine { line, number ->
  if (number < 2) return;
  dataRow = line.split(/\t/)
  nmRes = "http://example.org/NFYS16-M" + (number-1)
  rdf.addObjectProperty(rdfData,
    nmRes,
    "http://www.w3.org/1999/02/22-rdf-syntax-ns#type",
    "http://purl.obolibrary.org/obo/CHEBI_59999"
  )
  rdf.addObjectProperty(rdfData,
    nmRes,
    "http://purl.org/dc/terms/source",
    datasetStr
  )
  nmType = dataRow[5]
  if (nmType.startsWith("ENM_")) {
    rdf.addObjectProperty(rdfData,
      nmRes,
      "http://purl.org/dc/terms/type",
      "http://purl.enanomapper.org/onto/" + nmType
    )
  } else if (nmType.startsWith("NPO_")) {
    rdf.addObjectProperty(rdfData,
      nmRes,
      "http://purl.org/dc/terms/type",
      "http://purl.bioontology.org/ontology/npo#" + nmType
    )
  } else if (nmType.startsWith("CHEBI_")) {
    rdf.addObjectProperty(rdfData,
      nmRes,
      "http://purl.org/dc/terms/type",
      "http://purl.obolibrary.org/obo/" + nmType
    )
  } 
  rdf.addDataProperty(rdfData,
    nmRes,
    "http://www.w3.org/2000/01/rdf-schema#label",
    dataRow[1]
  )
  // core
  rdf.addObjectProperty(rdfData,
    nmRes,
    "http://purl.bioontology.org/ontology/npo#has_part",
    nmRes + "_core"
  )
  rdf.addDataProperty(rdfData,
    nmRes + "_core",
    "http://www.w3.org/2000/01/rdf-schema#label",
    dataRow[4]
  )
  rdf.addObjectProperty(rdfData,
    nmRes + "_core",
    "http://www.w3.org/1999/02/22-rdf-syntax-ns#type",
    "http://purl.bioontology.org/ontology/npo#NPO_1888"
  )
  possSMILES = dataRow[4]
  if (true) { // manually curated, otherwise: possSMILES.contains("[") || possSMILES.contains("=")) {
    smiRes = nmRes + "_core_smi"
    rdf.addObjectProperty(rdfData,
      nmRes + "_core",
      "http://semanticscience.org/resource/CHEMINF_000200",
      smiRes
    ) 
    rdf.addObjectProperty(rdfData,
      smiRes,
      "http://www.w3.org/1999/02/22-rdf-syntax-ns#type",
      "http://semanticscience.org/resource/CHEMINF_000018"
    )
    rdf.addDataProperty(rdfData,
      smiRes,
      "http://semanticscience.org/resource/SIO_000300",
      possSMILES
    )
  }
  // coating
  possSMILES = dataRow[6]
  if (possSMILES != null && !possSMILES.isEmpty()) {
    rdf.addObjectProperty(rdfData,
      nmRes,
      "http://purl.bioontology.org/ontology/npo#has_part",
      nmRes + "_coat"
    )
    rdf.addDataProperty(rdfData,
      nmRes + "_coat",
      "http://www.w3.org/2000/01/rdf-schema#label",
      dataRow[6]
    )
    rdf.addObjectProperty(rdfData,
      nmRes + "_coat",
      "http://www.w3.org/1999/02/22-rdf-syntax-ns#type",
      "http://purl.bioontology.org/ontology/npo#NPO_1367"
    )
    if (true) { // manually curated, otherwise: possSMILES.contains("[") || possSMILES.contains("=")) {
      smiRes = nmRes + "_coat_smi"
      rdf.addObjectProperty(rdfData,
        nmRes + "_coat",
        "http://semanticscience.org/resource/CHEMINF_000200",
        smiRes
      ) 
      rdf.addObjectProperty(rdfData,
        smiRes,
        "http://www.w3.org/1999/02/22-rdf-syntax-ns#type",
        "http://semanticscience.org/resource/CHEMINF_000018"
      )
      rdf.addDataProperty(rdfData,
        smiRes,
        "http://semanticscience.org/resource/SIO_000300",
        possSMILES
      )
    }
  }
  // size
  size = dataRow[3]
  if (size != null && size.length() > 0) {
    mgRes = nmRes + "_sizemg"
    rdf.addObjectProperty(rdfData,
      nmRes,
      "http://purl.obolibrary.org/obo/BFO_0000056",
      mgRes
    )
    rdf.addObjectProperty(rdfData,
      mgRes,
      "http://www.w3.org/1999/02/22-rdf-syntax-ns#type",
      "http://purl.obolibrary.org/obo/BAO_0000040"
    )
    sizeRes = nmRes + "_size"
    rdf.addObjectProperty(rdfData,
      mgRes,
      "http://purl.obolibrary.org/obo/OBI_0000299",
      sizeRes
    )
    rdf.addObjectProperty(rdfData,
      sizeRes,
      "http://www.w3.org/1999/02/22-rdf-syntax-ns#type",
      "http://www.bioassayontology.org/bao#BAO_0000179"
    )
    rdf.addDataProperty(rdfData,
      sizeRes,
      "http://www.w3.org/2000/01/rdf-schema#label",
      "primary particle size"
    )
    rdf.addDataProperty(rdfData,
      sizeRes,
      "http://semanticscience.org/resource/has-unit",
      "nm"
    )
    if (!size.contains("-")) {
      rdf.addDataProperty(rdfData,
        sizeRes,
        "http://semanticscience.org/resource/has-value",
        size
      )
    } else {
      rdf.addDataProperty(rdfData,
        sizeRes,
        "http://purl.obolibrary.org/obo/STATO_0000035",
        size
      )
    }
    rdf.addObjectProperty(rdfData,
      sizeRes,
      "http://purl.org/net/cito/usesDataFrom",
      "http://doi.org/" + dataRow[2]
    )
  }
  // zeta
  zeta = dataRow[7]
  if (zeta != null && zeta.length() > 0) {
    if (zeta.contains("(")) {
      zeta = zeta.split("\\(")[0]
    }
    mgRes = nmRes + "_zetamg"
    rdf.addObjectProperty(rdfData,
      nmRes,
      "http://purl.obolibrary.org/obo/BFO_0000056",
      mgRes
    )
    rdf.addObjectProperty(rdfData,
      mgRes,
      "http://www.w3.org/1999/02/22-rdf-syntax-ns#type",
      "http://purl.obolibrary.org/obo/BAO_0000040"
    )
    zetaRes = nmRes + "_zeta"
    rdf.addObjectProperty(rdfData,
      mgRes,
      "http://purl.obolibrary.org/obo/OBI_0000299",
      zetaRes
    )
    rdf.addObjectProperty(rdfData,
      zetaRes,
      "http://www.w3.org/1999/02/22-rdf-syntax-ns#type",
      "http://www.bioassayontology.org/bao#BAO_0000179"
    )
    rdf.addDataProperty(rdfData,
      zetaRes,
      "http://www.w3.org/2000/01/rdf-schema#label",
      "zeta potential"
    )
    rdf.addDataProperty(rdfData,
      zetaRes,
      "http://semanticscience.org/resource/has-unit",
      "mV"
    )
    rdf.addDataProperty(rdfData,
      zetaRes,
      "http://semanticscience.org/resource/has-value",
      zeta
    )
    rdf.addObjectProperty(rdfData,
      zetaRes,
      "http://purl.org/net/cito/usesDataFrom",
      "http://doi.org/" + dataRow[2]
    )
  }
  if (!processedDOIs.contains(dataRow[2])) {
    processedDOIs.add(dataRow[2]);
    doiURL = "http://doi.org/" + dataRow[2];
    println ("DOI download: " + doiURL)
    try { 
      doiContent = bioclipse.download(doiURL, "text/turtle")
      rdf.importFromString(doiData, doiContent, "TURTLE")
    } catch (Exception x) {}
  }
}

ui.append(outputFile, rdf.asTurtle(rdfData))
ui.append(doiOutputFile, rdf.asTurtle(doiData))
