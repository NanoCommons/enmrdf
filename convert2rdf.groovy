rdfData = rdf.createInMemoryStore()

dataFile = new File(bioclipse.fullPath("/2nd NanoSafety Forum for Young Scientists/data.tsv"))
dataFile.eachLine { line, number ->
  if (number < 2) return;
  dataRow = line.split(/\t/)
  print dataRow
}

rdf.asTurtle(rdfData)