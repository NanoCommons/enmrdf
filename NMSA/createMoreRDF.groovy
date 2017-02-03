outputFile = "/NMSA/moredata.ttl"
if (ui.fileExists(outputFile)) ui.remove(outputFile)

rdfData = rdf.createInMemoryStore()
rdf.addPrefix(rdfData, "abstract", "http://example.org/NMSA17/abstract/")
rdf.addPrefix(rdfData, "dcterms", "http://purl.org/dc/terms/")
rdf.addPrefix(rdfData, "nmsa", "http://example.org/NMSA17/onto/")

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

dataFile = new File(bioclipse.fullPath("/NMSA/moreresponses.tsv"))
dataFile.eachLine { line, number ->
  if (number < 2) return;
  dataRow = line.split(/\t/)
  abstractNo = dataRow[0]
  try { Integer.valueOf(abstractNo) } catch (Exception e) {
    println "Incorrect abstract number on line $number: $abstractNo"
    return
  }
  abstractRes = datasetStr + "/abstract/a" + abstractNo
  try {
    rdf.addDataProperty(rdfData,
      abstractRes,
      "http://purl.org/dc/terms/title",
      dataRow[1]
    )
    if (dataRow[2] != null && dataRow[2].trim().length() > 0) {
      rdf.addDataProperty(rdfData,
        abstractRes,
        "http://example.org/NMSA17/onto/guidebookID",
        dataRow[2]
      )
    }
    rdf.addDataProperty(rdfData,
      abstractRes,
      "http://example.org/NMSA17/onto/day",
      dataRow[3]
    )
    rdf.addDataProperty(rdfData,
      abstractRes,
      "http://example.org/NMSA17/onto/session",
      dataRow[4]
    )
    rdf.addDataProperty(rdfData,
      abstractRes,
      "http://example.org/NMSA17/onto/startTime",
      dataRow[5]
    )
    rdf.addDataProperty(rdfData,
      abstractRes,
      "http://example.org/NMSA17/onto/endTime",
      dataRow[6]
    )
  } catch (Exception exc) {
    println "Something wrong on line $number: " + exc.message + ", " + exc
    
  }
}

ui.append(outputFile, rdf.asTurtle(rdfData))
ui.open(outputFile)

