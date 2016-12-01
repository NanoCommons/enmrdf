outputFilename = "/NanoE-Tox/2190-4286-6-183-S2_Simpler.ttl"

dctNS = "http://purl.org/dc/terms/"
etoxNS = "http://egonw.github.com/enmrdf/nanoe-tox/"
npoNS = "http://purl.bioontology.org/ontology/npo#"
oboNS = "http://purl.obolibrary.org/obo/"
ssoNS = "http://semanticscience.org/resource/"

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
    core : [ label : "CuO", smiles : "[Cu]O" ]
  ],
  "DWCNT" : [
    core : [ label : "carbon", smiles : "[C]" ]
  ],
//Fe2O3
//Fe3O4
  "MWCNT" : [
    core : [ label : "carbon", smiles : "[C]" ]
  ],
  "MWCNT-COOH" : [
    core : [ label : "carbon", smiles : "[C]" ],
    coating : [ label : "COOH", smiles : "C(=O)O" ]
  ],
  "MWCNT-OH" : [
    core : [ label : "carbon", smiles : "[C]" ],
    coating : [ label : "OH", smiles : "O" ]
  ],
  "SWCNT" : [
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



rdfType = "http://www.w3.org/1999/02/22-rdf-syntax-ns#type"
rdfsLabel = "http://www.w3.org/2000/01/rdf-schema#label"
chebi59999 = "http://purl.obolibrary.org/obo/CHEBI_59999"

store = rdf.createInMemoryStore()
rdf.addPrefix(store, "dct", dctNS)
rdf.addPrefix(store, "etox", etoxNS)
rdf.addPrefix(store, "npo", npoNS)
rdf.addPrefix(store, "obo", oboNS)
rdf.addPrefix(store, "sso", ssoNS)

counter = 0;
new File(bioclipse.fullPath("/NanoE-Tox/2190-4286-6-183-S2_Simpler.csv")).eachLine { line ->
  fields = line.split("\t")
  counter++;

  // the next material
  enmIRI = "$etoxNS$counter"
  rdf.addObjectProperty(store, enmIRI, rdfType, chebi59999)

  // the name
  name = fields[0]
  rdf.addDataProperty(store, enmIRI, rdfsLabel, name)

  // the components (they all have a core)
  coreIRI = "${enmIRI}_core"
  rdf.addObjectProperty(store, enmIRI, "${npoNS}has_part", coreIRI)
  rdf.addObjectProperty(store, coreIRI, rdfType, "${npoNS}NPO_1597")
  
  if (nanomaterials[name]) {
    if (nanomaterials[name].iri) {
      rdf.addObjectProperty(store, enmIRI, "${dctNS}type", nanomaterials[name].iri)
    }
    if (nanomaterials[name].core) {
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
}

if (ui.fileExists(outputFilename)) ui.remove(outputFilename)
output = ui.newFile(outputFilename, rdf.asTurtle(store) )
ui.open(output)
