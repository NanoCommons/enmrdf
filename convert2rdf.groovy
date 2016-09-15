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
  rdf.addDataProperty(rdfData,
    nmRes,
    "http://www.w3.org/2000/01/rdf-schema#label",
    dataRow[1]
  )
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
  if (possSMILES.contains("[") || possSMILES.contains("=")) {
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
  // size
  size = dataRow[3]
  if (size != null && size.length() > 0 && !size.contains("-")) {
    mgRes = nmRes + "_sizemg"
    rdf.addObjectProperty(rdfData,
      nmRes,
      "http://purl.obolibrary.org/obo/BFO_0000056",
      mgRes
    )
    rdf.addObjectProperty(rdfData,
      mgRes,
      "http://www.w3.org/1999/02/22-rdf-syntax-ns#type",
      "http://purl.obolibrary.org/obo/CHEBI_59999"
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
    rdf.addDataProperty(rdfData,
      sizeRes,
      "http://semanticscience.org/resource/has-value",
      size
    )
  }
}

print rdf.asTurtle(rdfData)