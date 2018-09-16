
outputFilename = "/NanoE-Tox/2190-4286-6-183-S2_Simpler.ttl"

baoNS = "http://www.bioassayontology.org/bao#"
dcNS = "http://purl.org/dc/elements/1.1/"
dctNS = "http://purl.org/dc/terms/"
enmNS = "http://purl.enanomapper.org/onto/"
etoxNS = "http://egonw.github.com/enmrdf/nanoe-tox/"
npoNS = "http://purl.bioontology.org/ontology/npo#"
oboNS = "http://purl.obolibrary.org/obo/"
ssoNS = "http://semanticscience.org/resource/"
voidNS = "http://rdfs.org/ns/void#"
xsdNS = "http://www.w3.org/2001/XMLSchema#"

excelCorrections = [
  "01-feb": "1-2",
  "01-mrt": "1-3",
  "02-mei": "2-5",
  "03-aug": "3-8",
  "01-okt": "1-10",
  "02-okt": "2-10",
  "04-okt": "4-10",
  "05-okt": "5-10",
  "08-okt": "8-10"
]

nanomaterials = [
  "Ag" : [
    iri : "http://purl.bioontology.org/ontology/npo#NPO_1384",
    core : [ label : "silver", smiles : "[Ag]" ]
  ],
  "fullerene" : [
    iri : "http://purl.obolibrary.org/obo/CHEBI_33416",
    core : [ label : "carbon", smiles : "[C]" ]
  ],
  "C60 fullerene" : [
    iri : "http://purl.obolibrary.org/obo/CHEBI_33128",
    core : [ label : "carbon", smiles : "c12c3c4c5c1c1c6c7c2c2c8c3c3c9c4c4c%10c5c5c1c1c6c6c%11c7c2c2c7c8c3c3c8c9c4c4c9c%10c5c5c1c1c6c6c%11c2c2c7c3c3c8c4c4c9c5c1c1c6c2c3c41" ]
  ],
  "C70 fullerene" : [
    iri : "http://purl.obolibrary.org/obo/CHEBI_33195",
    core : [ label : "carbon", smiles : "c12c3c4c5c1c1c6c7c2c2c8c3c3c9c4c4c%10c5c5c1c1c6c6c%11c7c2c2c7c8c3c3c8c9c4c4c9c%10c5c5c1c1c6c6c%10c%11c2c2c%11c7c3c3c%11c7c(c%102)c2c6c1c1c5c9c5c6c4c8c3c6c7c2c15" ]
  ],
  "CeO2" : [
    iri : "http://purl.enanomapper.org/onto/ENM_9000006",
    core : [ label : "CeO2", smiles : "O=[Ce]=O" ]
  ],
  "CuO" : [
    iri : "http://purl.bioontology.org/ontology/npo#NPO_1544",
    core : [ label : "CuO", smiles : "[Cu]=O" ]
  ],
  "DWCNT" : [
    iri : "http://purl.bioontology.org/ontology/npo#NPO_301",
    core : [ label : "carbon", smiles : "[C]" ]
  ],
  "Fe2O3" : [
    iri : "http://purl.bioontology.org/ontology/npo#NPO_1550",
    core : [ label : "Fe2O3" ]
  ],
  "Fe3O4" : [
    iri : "http://purl.bioontology.org/ontology/npo#NPO_1548",
    core : [ label : "Fe3O4" ]
  ],
  "MWCNT" : [
    iri : "http://purl.bioontology.org/ontology/npo#NPO_354",
    core : [ label : "carbon", smiles : "[C]" ]
  ],
  "MWCNT-COOH" : [
    iri : "http://purl.bioontology.org/ontology/npo#NPO_354",
    core : [ label : "carbon", smiles : "[C]" ],
    coating : [ label : "COOH", smiles : "C(=O)O" ]
  ],
  "MWCNT-OH" : [
    iri : "http://purl.bioontology.org/ontology/npo#NPO_354",
    core : [ label : "carbon", smiles : "[C]" ],
    coating : [ label : "OH", smiles : "O" ]
  ],
  "SWCNT" : [
    iri : "http://purl.bioontology.org/ontology/npo#NPO_943",
    core : [ label : "carbon", smiles : "[C]" ]
  ],
  "TiO2" : [
    iri : "http://purl.obolibrary.org/obo/CHEBI_51050",
    core : [ label : "TiO2", smiles : "O=[Ti]=O" ]
  ],
  "ZnO" : [
    iri : "http://purl.bioontology.org/ontology/npo#NPO_1542",
    core : [ label : "ZnO", smiles : "[Zn]=O" ]
  ]
]

materials = new java.util.HashMap()

rdfType = "http://www.w3.org/1999/02/22-rdf-syntax-ns#type"
rdfsLabel = "http://www.w3.org/2000/01/rdf-schema#label"
chebi59999 = "http://purl.obolibrary.org/obo/CHEBI_59999"

store = rdf.createInMemoryStore()
rdf.addPrefix(store, "bao", baoNS)
rdf.addPrefix(store, "dc", dcNS)
rdf.addPrefix(store, "dct", dctNS)
rdf.addPrefix(store, "enm", enmNS)
rdf.addPrefix(store, "etox", etoxNS)
rdf.addPrefix(store, "npo", npoNS)
rdf.addPrefix(store, "obo", oboNS)
rdf.addPrefix(store, "sso", ssoNS)
rdf.addPrefix(store, "void", voidNS)

datasetIRI = "${etoxNS}dataset"
rdf.addObjectProperty(store, datasetIRI, rdfType, "${voidNS}DataSet")
rdf.addDataProperty(store, datasetIRI, "${dctNS}title", "NanoE-Tox RDF")
rdf.addObjectProperty(store, datasetIRI, "${dctNS}license", "https://creativecommons.org/licenses/by/4.0/")

counter = 0;
assayCount = 0;
new File(bioclipse.fullPath("/NanoE-Tox/2190-4286-6-183-S2.csv")).eachLine { line ->
  fields = line.split("\t")
  newMaterial = false
  
  // the name
  name = fields[0]
  // the supplier
  supplier = fields[1]
  // the diameter
  diameter = fields[5]

  // unique id
  uniqueKey = name + supplier + diameter 

  if (materials.containsKey(uniqueKey)) {
    materialCounter = materials.get(uniqueKey)
  } else {
    newMaterial = true;
    counter++;
    materialCounter = counter;
    materials.put(uniqueKey, counter);
  }

  // the next material
  enmIRI = "${etoxNS}m$materialCounter"
  rdf.addObjectProperty(store, enmIRI, rdfType, chebi59999)

  rdf.addDataProperty(store, enmIRI, rdfsLabel, name)

  // the components (they all have a core)
  coreIRI = "${enmIRI}_core"
  rdf.addObjectProperty(store, enmIRI, "${npoNS}has_part", coreIRI)
  rdf.addObjectProperty(store, coreIRI, rdfType, "${npoNS}NPO_1597")

  if (newMaterial) {
    if (nanomaterials[name]) {
      if (nanomaterials[name].iri) {
        rdf.addObjectProperty(store, enmIRI, "${dctNS}type", nanomaterials[name].iri)
      }
      if (nanomaterials[name].core && nanomaterials[name].core.smiles) {
        smilesIRI = "${coreIRI}_smiles"
        rdf.addObjectProperty(store, coreIRI, "${ssoNS}CHEMINF_000200", smilesIRI)
        rdf.addObjectProperty(store, smilesIRI, rdfType, "${ssoNS}CHEMINF_000018")
        rdf.addDataProperty(store, smilesIRI, "${ssoNS}SIO_000300", nanomaterials[name].core.smiles)
        rdf.addDataProperty(store, smilesIRI, rdfsLabel, nanomaterials[name].core.label)
      }
      if (nanomaterials[name].coating) {
        coatingIRI = "${enmIRI}_coating"
        rdf.addObjectProperty(store, enmIRI, "${npoNS}has_part", coatingIRI)
        smilesIRI = "${coatingIRI}_smiles"
        rdf.addObjectProperty(store, coatingIRI, "${ssoNS}CHEMINF_000200", smilesIRI)
        rdf.addObjectProperty(store, smilesIRI, rdfType, "${ssoNS}CHEMINF_000018")
        rdf.addDataProperty(store, smilesIRI, "${ssoNS}SIO_000300", nanomaterials[name].coating.smiles)
        rdf.addDataProperty(store, smilesIRI, rdfsLabel, nanomaterials[name].coating.label)
      }
    }

    if (diameter && !diameter.contains("N/A") && !diameter.contains("(")) {
      diameter = diameter.trim()
      diameter = diameter.replace(",", ".")
      
      assayCount++;
      assayIRI = "${enmIRI}_sizeAssay" + assayCount
      measurementGroupIRI = "${enmIRI}_sizeMeasurementGroup" + assayCount
      endpointIRI = "${enmIRI}_sizeEndpoint"

      // the assay
      rdf.addObjectProperty(store, assayIRI, rdfType, "${baoNS}BAO_0000015")
      rdf.addObjectProperty(store, assayIRI, rdfType, "${npoNS}NPO_1539")
      rdf.addDataProperty(store, assayIRI, "${dcNS}title", "Diameter")
      rdf.addObjectProperty(store, assayIRI, "${baoNS}BAO_0000209", measurementGroupIRI)

      // the measurement group
      rdf.addObjectProperty(store, measurementGroupIRI, rdfType, "${baoNS}BAO_0000040")
      rdf.addObjectProperty(store, measurementGroupIRI, "${oboNS}OBI_0000299", endpointIRI)

      // the endpoint
      rdf.addObjectProperty(store, endpointIRI, rdfType, "${baoNS}BAO_0000179")
      rdf.addObjectProperty(store, endpointIRI, "${oboNS}OBI_0000299", endpointIRI)
      rdf.addObjectProperty(store, endpointIRI, "${oboNS}IAO_0000136", enmIRI)
 
      if (diameter.contains("-")) {
        if (excelCorrections.containsKey(diameter.trim().toLowerCase())) {
          // print("Replaced " + diameter + " with ")
          diameter = excelCorrections.get(diameter.trim().toLowerCase())
          // println(diameter)
        }
        rdf.addTypedDataProperty(store, assayIRI, "${oboNS}STATO_0000035", diameter, "${xsdNS}string")
        rdf.addDataProperty(store, assayIRI, "${ssoNS}has-unit", "nm")
      } else if (diameter.contains("±")) {
        rdf.addTypedDataProperty(store, assayIRI, "${oboNS}STATO_0000035", diameter, "${xsdNS}string")
        rdf.addDataProperty(store, assayIRI, "${ssoNS}has-unit", "nm")
      } else if (diameter.contains("<")) {
      } else {
        rdf.addTypedDataProperty(store, assayIRI, "${ssoNS}has-value", diameter, "${xsdNS}double")
        rdf.addDataProperty(store, assayIRI, "${ssoNS}has-unit", "nm")
      }
    }
  }

  // the zeta potential
  zp = fields[14].trim()
  
  if (zp && !zp.contains("N/A") &&  zp != "positive" && !zp.contains("(") && !zp.contains("at") &&
      !zp.contains("-------------------")) {
    zp = zp.replace("mV","").trim()
    assayCount++
    assayIRI = "${enmIRI}_zpAssay" + assayCount
    measurementGroupIRI = "${enmIRI}_zpMeasurementGroup" + assayCount
    endpointIRI = "${enmIRI}_zpEndpoint"

    // the assay
    rdf.addObjectProperty(store, assayIRI, rdfType, "${npoNS}NPO_1302")
    rdf.addDataProperty(store, assayIRI, "${dcNS}title", "Zeta potential")
    rdf.addObjectProperty(store, assayIRI, "${baoNS}BAO_0000209", measurementGroupIRI)

    // the measurement group
    rdf.addObjectProperty(store, measurementGroupIRI, rdfType, "${baoNS}BAO_0000040")
    rdf.addObjectProperty(store, measurementGroupIRI, "${oboNS}OBI_0000299", endpointIRI)

    // the endpoint
    rdf.addObjectProperty(store, endpointIRI, rdfType, "${baoNS}BAO_0000179")
    rdf.addObjectProperty(store, endpointIRI, "${oboNS}OBI_0000299", endpointIRI)
    rdf.addObjectProperty(store, endpointIRI, "${oboNS}IAO_0000136", enmIRI)
 
    zp = zp.replace(",", ".")
    zp = zp.replace("ca", "").trim()
    if (zp.substring(1).contains("-")) {
      if (excelCorrections.containsKey(zp.trim().toLowerCase())) {
        // print("Replaced " + zp + " with ")
        zp = excelCorrections.get(zp.trim().toLowerCase())
        // println(zp)
      }
      rdf.addTypedDataProperty(store, assayIRI, "${oboNS}STATO_0000035", zp, "${xsdNS}string")
      rdf.addDataProperty(store, assayIRI, "${ssoNS}has-unit", "mV")
    } else if (zp.contains("...")) {
      zp = zp.replace("...","-")
      rdf.addTypedDataProperty(store, assayIRI, "${oboNS}STATO_0000035", zp, "${xsdNS}string")
      rdf.addDataProperty(store, assayIRI, "${ssoNS}has-unit", "mV")
    } else if (zp.contains("±")) {
      rdf.addTypedDataProperty(store, assayIRI, "${oboNS}STATO_0000035", zp, "${xsdNS}string")
      rdf.addDataProperty(store, assayIRI, "${ssoNS}has-unit", "mV")
    } else if (zp.contains("<")) {
    } else {
      rdf.addTypedDataProperty(store, assayIRI, "${ssoNS}has-value", zp, "${xsdNS}double")
      rdf.addDataProperty(store, assayIRI, "${ssoNS}has-unit", "mV")
    }
  }

  // the surface areas
  prop = fields[8].trim()
  
  if (prop && !prop.contains("N/A") && !prop.contains("(") && !prop.contains(">") && !prop.contains("<")) {
    units = "m2/g"
    if (prop.contains("nm2")) units = "nm2"
    prop = prop.replace("m2/g","").replace("nm2","").trim()
    assayCount++
    assayIRI = "${enmIRI}_saAssay" + assayCount
    measurementGroupIRI = "${enmIRI}_saMeasurementGroup" + assayCount
    endpointIRI = "${enmIRI}_saEndpoint"

    // the assay
    rdf.addObjectProperty(store, assayIRI, rdfType, "${npoNS}NPO_1235")
    rdf.addDataProperty(store, assayIRI, "${dcNS}title", "Surface Area")
    rdf.addObjectProperty(store, assayIRI, "${baoNS}BAO_0000209", measurementGroupIRI)

    // the measurement group
    rdf.addObjectProperty(store, measurementGroupIRI, rdfType, "${baoNS}BAO_0000040")
    rdf.addObjectProperty(store, measurementGroupIRI, "${oboNS}OBI_0000299", endpointIRI)

    // the endpoint
    rdf.addObjectProperty(store, endpointIRI, rdfType, "${baoNS}BAO_0000179")
    rdf.addObjectProperty(store, endpointIRI, "${oboNS}OBI_0000299", endpointIRI)
    rdf.addObjectProperty(store, endpointIRI, "${oboNS}IAO_0000136", enmIRI)
 
    prop = prop.replace(",", ".")
    if (prop.substring(1).contains("-")) {
      rdf.addTypedDataProperty(store, assayIRI, "${oboNS}STATO_0000035", prop, "${xsdNS}string")
      rdf.addDataProperty(store, assayIRI, "${ssoNS}has-unit", units)
    } else if (prop.contains("±")) {
      rdf.addTypedDataProperty(store, assayIRI, "${oboNS}STATO_0000035", prop, "${xsdNS}string")
      rdf.addDataProperty(store, assayIRI, "${ssoNS}has-unit", units)
    } else if (prop.contains("<")) {
    } else {
      rdf.addTypedDataProperty(store, assayIRI, "${ssoNS}has-value", prop, "${xsdNS}double")
      rdf.addDataProperty(store, assayIRI, "${ssoNS}has-unit", units)
    }
  }

}

if (ui.fileExists(outputFilename)) ui.remove(outputFilename)
output = ui.newFile(outputFilename, rdf.asTurtle(store) )
ui.open(output)
