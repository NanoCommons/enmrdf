
outputFilename = "/NanoE-Tox/2190-4286-6-183-S2_Simpler.ttl"
logFilename = "/NanoE-Tox/conversion.log"
logMessages = ""

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

literature = [
  "art1": [
    title: "Systematic and Quantitative Investigation of the Mechanism of Carbon Nanotubes",
    doi: "10.1021/es301802g"
  ],
  "art2": [
    title: "Toxicity of carbon nanotubes to freshwater aquatic invertebrates",
    doi: "10.1002/etc.1888"
  ],
  "art3": [
    title: "Uptake, Translocation, and Transmission of Carbon Nanomaterials in Rice Plants",
    doi: "10.1002/smll.200801556"
  ],
  "art4": [
    title: "Life-cycle effects of single-walled carbon nanotubes (SWNTs) on an estuarine meiobenthic copepod",
    doi: "10.1021/es060407p"
  ],
  "art5": [
    title: "A Study of Antibacterial Activity of Individually Dispersed Pristine Single-Walled Carbon Nanotube",
    doi: "10.1021/nn901252r"
  ],
  "art6": [
    title: "Chronic toxicity of double-walled carbon nanotubes to three marine organisms",
    doi: "10.2217/nnm.10.59"
  ],
  "art7": [
    title: "Are Carbon Nanotube Effects on Green Algae Caused by Shading and Agglomeration",
    doi: "10.1021/es200506b"
  ],
  "art8": [
    title: "Ecotoxicity of Selected Nano-Materials to Aquatic Organisms",
    doi: "10.1002/tox.20402"
  ],
  "art9": [
    title: "Profiling of the reactive oxygen species-related ecotoxicity of CuO, ZnO, TiO",
    doi: "10.1007/s00216-010-3962-7"
  ],
  "art10": [
    title: "Fate and Effects of CeO2 Nanoparticles in Aquatic Ecotoxicity Tests",
    doi: "10.1021/es9002444"
  ],
  "art11": [
    title: "Acute toxicity of Ag and CuO nanoparticle suspensions against Daphnia magna",
    doi: "10.1016/j.jhazmat.2012.05.066"
  ],
  "art12": [
    title: "Toxicity of two types of silver nanoparticles to aquatic crustaceans Daphnia magna and Thamnocephalus platyurus",
    doi: "10.1007/s11356-012-1290-5"
  ],
  "art13": [
    title: "Qualitative toxicity assessment of silver nanoparticles on the fresh water bacterial isolates and consortium at low level of exposure concentration",
    doi: "10.1016/j.ecoenv.2014.05.033"
  ],
  "art14": [
    title: "Physicochemical Characterization and Ecotoxicological Assessment of CeO2 Nanoparticles Using Two Aquatic Microorganisms",
    doi: "10.1093/toxsci/kfq311"
  ],
  "art15": [
    title: "Ecotoxicity of nanoparticles of CuO and ZnO in natural water",
    doi: "10.1016/j.envpol.2009.08.017"
  ],
  "art16": [
    title: "The effect of humic acids on the cytotoxicity of silver nanoparticles to a natural aquatic bacterial assemblage",
    doi: "10.1016/j.scitotenv.2010.08.030"
  ],
  "art17": [
    title: "Acute and chronic effects of nano- and non-nano-scale TiO2 and ZnO particles on mobility and reproduction of the freshwater invertebrate Daphnia magna",
    doi: "10.1016/j.chemosphere.2009.06.025"
  ],
  "artXX": [
    title: "XXXX",
    doi: ""
  ],
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
rdf.addObjectProperty(store, datasetIRI, rdfType, "${voidNS}Dataset")
rdf.addDataProperty(store, datasetIRI, "${dctNS}title", "NanoE-Tox RDF")
rdf.addDataProperty(store, datasetIRI, "${dctNS}description", "RDF version of the data from Beilstein J. Nanotechnol. 2015, 6, 1788–1804. doi:10.3762/bjnano.6.183.")
rdf.addDataProperty(store, datasetIRI, "${dctNS}publisher", "Egon Willighagen")
rdf.addObjectProperty(store, datasetIRI, "${dctNS}license", "https://creativecommons.org/licenses/by/4.0/")

counter = 0;
assayCount = 0;
toxCount = 0;
citations = 0;
new File(bioclipse.fullPath("/NanoE-Tox/2190-4286-6-183-S2.csv")).eachLine { line ->
  fields = line.split("\t")
  newMaterial = false
  
  // the name
  name = fields[0]
  // the supplier
  supplier = fields[1]
  // the diameter
  diameter = fields[5]
  
  // literature
  artTitle = fields[28].trim()

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

  doi = null
  recognized = false
  literature.each { article ->
    mapDOI = article.value.doi
    mapArticle = article.value.title
    if (artTitle.contains(mapArticle)) {
      doi = mapDOI
      recognized = true
    } 
  }
  if (!recognized) {
    logMessages += "Unrecognized article title: $artTitle \n"
  }

  // the next material
  enmIRI = "${etoxNS}m$materialCounter"
  rdf.addObjectProperty(store, enmIRI, rdfType, chebi59999)
  rdf.addObjectProperty(store, enmIRI, "${dctNS}source" , datasetIRI)
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
      diameter = diameter.replace(",", ".")
                         .replace("~", "")
                         .trim()
      
      assayCount++;
      assayIRI = "${enmIRI}_sizeAssay" + assayCount
      measurementGroupIRI = "${enmIRI}_sizeMeasurementGroup" + assayCount
      endpointIRI = "${measurementGroupIRI}_sizeEndpoint"

      // the assay
      // rdf.addObjectProperty(store, assayIRI, rdfType, "${baoNS}BAO_0000015")
      // rdf.addDataProperty(store, assayIRI, "${dcNS}title", "Diameter Assay")
      // rdf.addObjectProperty(store, assayIRI, "${baoNS}BAO_0000209", measurementGroupIRI)
      rdf.addObjectProperty(store, enmIRI, "${oboNS}BFO_0000056", measurementGroupIRI)

      // the measurement group
      rdf.addObjectProperty(store, measurementGroupIRI, rdfType, "${baoNS}BAO_0000040")
      rdf.addObjectProperty(store, measurementGroupIRI, "${oboNS}OBI_0000299", endpointIRI)

      // the endpoint
      rdf.addObjectProperty(store, endpointIRI, rdfType, "${npoNS}NPO_1539")
      rdf.addObjectProperty(store, endpointIRI, "${oboNS}IAO_0000136", enmIRI)
      rdf.addDataProperty(store, endpointIRI, rdfsLabel, "Diameter")
 
      if (diameter.contains("-") || diameter.contains("–")) {
        diameter = diameter.replace("–","-")
        if (excelCorrections.containsKey(diameter.trim().toLowerCase())) {
          logMessages += "Replaced " + diameter + " with "
          diameter = excelCorrections.get(diameter.trim().toLowerCase())
          logMessages += diameter + "\n"
        }
        rdf.addTypedDataProperty(store, endpointIRI, "${oboNS}STATO_0000035", diameter, "${xsdNS}string")
        rdf.addDataProperty(store, endpointIRI, "${ssoNS}has-unit", "nm")
      } else if (diameter.contains("±")) {
        rdf.addTypedDataProperty(store, endpointIRI, "${oboNS}STATO_0000035", diameter, "${xsdNS}string")
        rdf.addDataProperty(store, endpointIRI, "${ssoNS}has-unit", "nm")
      } else if (diameter.trim().startsWith("<")) {
        rdf.addTypedDataProperty(store, endpointIRI, "${oboNS}STATO_0000035", "1-$diameter", "${xsdNS}string")
        rdf.addDataProperty(store, endpointIRI, "${ssoNS}has-unit", "nm")
      } else if (diameter.contains("<")) {
        logMessages += "Unrecognized diameter value: $diameter \n"
      } else if (diameter.contains(";")) {
        logMessages += "Unrecognized diameter value: $diameter \n"
      } else {
        rdf.addTypedDataProperty(store, endpointIRI, "${ssoNS}has-value", diameter, "${xsdNS}double")
        rdf.addDataProperty(store, endpointIRI, "${ssoNS}has-unit", "nm")
      }
    }
  
    // the zeta potential
    zp = fields[14].trim()
    zp = zp.replace("−", "-")
    
    if (zp && !zp.contains("N/A") &&  zp != "positive" && !zp.contains("(") && !zp.contains("at") &&
        !zp.contains("-------------------")) {
      zp = zp.replace("mV","").trim()
      assayCount++
      assayIRI = "${enmIRI}_zpAssay" + assayCount
      measurementGroupIRI = "${enmIRI}_zpMeasurementGroup" + assayCount
      endpointIRI = "${measurementGroupIRI}_zpEndpoint"
  
      // the assay
      // rdf.addObjectProperty(store, assayIRI, rdfType, "${baoNS}BAO_0000015")
      // rdf.addDataProperty(store, assayIRI, "${dcNS}title", "Zeta potential")
      // rdf.addObjectProperty(store, assayIRI, "${baoNS}BAO_0000209", measurementGroupIRI)
      rdf.addObjectProperty(store, enmIRI, "${oboNS}BFO_0000056", measurementGroupIRI)
  
      // the measurement group
      rdf.addObjectProperty(store, measurementGroupIRI, rdfType, "${baoNS}BAO_0000040")
      rdf.addObjectProperty(store, measurementGroupIRI, "${oboNS}OBI_0000299", endpointIRI)
  
      // the endpoint
      rdf.addObjectProperty(store, endpointIRI, rdfType, "${npoNS}NPO_1302")
      rdf.addObjectProperty(store, endpointIRI, "${oboNS}IAO_0000136", enmIRI)
      rdf.addDataProperty(store, endpointIRI, rdfsLabel, "Zeta potential")
     
      zp = zp.replace(",", ".")
      zp = zp.replace("ca", "").trim()
      zp = zp.replace("- 51.6", "-51.6").trim()
      if (zp.substring(1).contains("-")) {
        if (excelCorrections.containsKey(zp.trim().toLowerCase())) {
          // print("Replaced " + zp + " with ")
          zp = excelCorrections.get(zp.trim().toLowerCase())
          // println(zp)
        }
        rdf.addTypedDataProperty(store, endpointIRI, "${oboNS}STATO_0000035", zp, "${xsdNS}string")
        rdf.addDataProperty(store, endpointIRI, "${ssoNS}has-unit", "mV")
      } else if (zp.contains("...")) {
        zp = zp.replace("...","-")
        rdf.addTypedDataProperty(store, endpointIRI, "${oboNS}STATO_0000035", zp, "${xsdNS}string")
        rdf.addDataProperty(store, endpointIRI, "${ssoNS}has-unit", "mV")
      } else if (zp.contains("±")) {
        rdf.addTypedDataProperty(store, endpointIRI, "${oboNS}STATO_0000035", zp, "${xsdNS}string")
        rdf.addDataProperty(store, endpointIRI, "${ssoNS}has-unit", "mV")
      } else if (zp.contains("<")) {
        logMessages += "Unrecognized zeta potential value: $zp \n"
      } else {
        rdf.addTypedDataProperty(store, endpointIRI, "${ssoNS}has-value", zp, "${xsdNS}double")
        rdf.addDataProperty(store, endpointIRI, "${ssoNS}has-unit", "mV")
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
      endpointIRI = "${measurementGroupIRI}_saEndpoint"
  
      // the assay
      // rdf.addObjectProperty(store, assayIRI, rdfType, "${baoNS}BAO_0000015")
      // rdf.addDataProperty(store, assayIRI, "${dcNS}title", "Surface Area")
      // rdf.addObjectProperty(store, assayIRI, "${baoNS}BAO_0000209", measurementGroupIRI)
      rdf.addObjectProperty(store, enmIRI, "${oboNS}BFO_0000056", measurementGroupIRI)
  
      // the measurement group
      rdf.addObjectProperty(store, measurementGroupIRI, rdfType, "${baoNS}BAO_0000040")
      rdf.addObjectProperty(store, measurementGroupIRI, "${oboNS}OBI_0000299", endpointIRI)
  
      // the endpoint
      rdf.addObjectProperty(store, endpointIRI, rdfType, "${npoNS}NPO_1235")
      rdf.addObjectProperty(store, endpointIRI, "${oboNS}IAO_0000136", enmIRI)
      rdf.addDataProperty(store, endpointIRI, rdfsLabel, "Surface Area")
   
      prop = prop.replace(",", ".")
      if (prop.substring(1).contains("-") || prop.contains("–")) {
        prop = prop.replace("–","-")
        rdf.addTypedDataProperty(store, endpointIRI, "${oboNS}STATO_0000035", prop, "${xsdNS}string")
        rdf.addDataProperty(store, endpointIRI, "${ssoNS}has-unit", units)
      } else if (prop.contains("±")) {
        rdf.addTypedDataProperty(store, endpointIRI, "${oboNS}STATO_0000035", prop, "${xsdNS}string")
        rdf.addDataProperty(store, endpointIRI, "${ssoNS}has-unit", units)
      } else if (prop.contains("<")) {
        logMessages += "Unrecognized property value: $prop \n"
      } else {
        rdf.addTypedDataProperty(store, endpointIRI, "${ssoNS}has-value", prop, "${xsdNS}double")
        rdf.addDataProperty(store, endpointIRI, "${ssoNS}has-unit", units)
      }
    }

  }

  // the toxicology
  toxtype = fields[22].trim()
  prop = fields[23].trim()

  recognizedToxicities = [
    "EC10": "http://www.bioassayontology.org/bao#BAO_0001263",
    "EC20": "http://www.bioassayontology.org/bao#BAO_0001235",
    "EC25": "http://www.bioassayontology.org/bao#BAO_0001264",
    "EC30": "http://www.bioassayontology.org/bao#BAO_0000599",
    "EC50": "http://www.bioassayontology.org/bao#BAO_0000188",
    "EC80": "http://purl.enanomapper.org/onto/ENM_0000053",
    "EC90": "http://www.bioassayontology.org/bao#BAO_0001237",
    "IC50": "http://www.bioassayontology.org/bao#BAO_0000190",
    "LC50": "http://www.bioassayontology.org/bao#BAO_0002145",
    "MIC":  "http://www.bioassayontology.org/bao#BAO_0002146",
    "NOEC": "http://purl.enanomapper.org/onto/ENM_0000060",
    "NOEL": "http://purl.enanomapper.org/onto/ENM_0000056",
    "LOEC": "http://purl.enanomapper.org/onto/ENM_8100000"
  ]  

  recognizedUnits = [
    "mg/L": "mg/L",
    "mg/ml": "mg/ml",
    "mg/mL": "mg/mL",
    "µg/L of food": "µg/L",
    "µg/L": "µg/L",
    "µg/mL": "µg/mL",
    "mg Ag/L": "mg/L",
    "mg Cu/L": "mg/L",
    "mg Zn/L": "mg/L",
    "µg dissolved Ag/L": "µg/L",
    "µg dissolved Cu/L": "µg/L",
    "µg dissolved Zn/L": "µg/L",
    "µg Ag/L": "µg/L",
    "fmol/L": "fmol/L",
    "g/L": "g/L",
    "g/l": "g/l",
    
    "nM": "nM",
    "mM": "mM",
    "µM": "µM",
    "ppm": "ppm",
    
    "mmol/g": "mmol/g",
    "nmol/g fresh weight": "nmol/g",
    "µg Cu/g": "µg/g",
    "mg Ag/kg": "mg/kg",
    "mg Zn/kg": "mg/kg",
    "mg Zn/kg  d.w.": "mg/kg",
    "mg/kg of dry feed": "mg/kg", 
    "mg/kg": "mg/kg",
    "µg/g dry weight sediment": "µg/g", 
    "µg/g": "µg/g",
    "g/kg": "g/kg"
  ]

  if (toxtype && prop && !prop.contains("N/A") && !prop.contains("%") &&
      !prop.contains(">") && !prop.contains("(") && !prop.contains("≤")) {
    units = "mg/L"
    for (unit in recognizedUnits.keySet()) {
      if (prop.contains(unit)) {
        units = "µg/L";
        prop = prop.replace(unit,"");
      }
    }
    prop = prop.replace("~","")
               .replace("d.w.", "")
               .replace("dry food", "")
               .replace("of food", "")
               .trim()
    
    if (!recognizedToxicities.containsKey(toxtype)) {
      logMessages += "Unrecognized TOX endpoint: $toxtype \n"
    } else if (prop.contains("/")) {
      logMessages += "Unrecognized TOX unit: $prop \n"
    } else {
      recogType = recognizedToxicities.get(toxtype)
    
      // ok,recognized
      assayCount++
      toxCount++
      assayTypeCode = toxtype.toLowerCase();
      assayIRI = "${enmIRI}_${assayTypeCode}Assay" + assayCount
      measurementGroupIRI = "${enmIRI}_${assayTypeCode}MeasurementGroup" + assayCount
      endpointIRI = "${measurementGroupIRI}_${assayTypeCode}Endpoint"

      // the assay
      rdf.addObjectProperty(store, assayIRI, rdfType, "${baoNS}BAO_0000015")
      rdf.addDataProperty(store, assayIRI, "${dcNS}title", toxtype)
      rdf.addObjectProperty(store, assayIRI, "${baoNS}BAO_0000209", measurementGroupIRI)
      if (doi != null) {
        articleIRI = "https://doi.org/${doi}"
        rdf.addObjectProperty(store, assayIRI, "${dctNS}source", articleIRI)
        citations++
      }
      rdf.addObjectProperty(store, enmIRI, "${oboNS}BFO_0000056", measurementGroupIRI)

      // the measurement group
      rdf.addObjectProperty(store, measurementGroupIRI, rdfType, "${baoNS}BAO_0000040")
      rdf.addObjectProperty(store, measurementGroupIRI, "${oboNS}OBI_0000299", endpointIRI)

      // the endpoint
      rdf.addObjectProperty(store, endpointIRI, rdfType, recogType)
      rdf.addObjectProperty(store, endpointIRI, "${oboNS}IAO_0000136", enmIRI)
      rdf.addDataProperty(store, endpointIRI, rdfsLabel, toxtype)
 
      prop = prop.replace(",", ".")
                 .replace("...","-")
                 .replace(" ","")
                 .trim()
      if (prop.substring(1).contains("-")) {
        rdf.addTypedDataProperty(store, endpointIRI, "${oboNS}STATO_0000035", prop, "${xsdNS}string")
        rdf.addDataProperty(store, endpointIRI, "${ssoNS}has-unit", units)
      } else if (prop.contains("±")) {
        rdf.addTypedDataProperty(store, endpointIRI, "${oboNS}STATO_0000035", prop, "${xsdNS}string")
        rdf.addDataProperty(store, endpointIRI, "${ssoNS}has-unit", units)
      } else if (prop.trim().startsWith("<")) {
        rdf.addTypedDataProperty(store, endpointIRI, "${oboNS}STATO_0000035", "0-$prop", "${xsdNS}string")
        rdf.addDataProperty(store, endpointIRI, "${ssoNS}has-unit", units)
      } else if (prop.contains("<")) {
        logMessages += "Unrecognized tox value: $prop \n"
      } else if (prop.contains(";")) {
      } else {
        rdf.addTypedDataProperty(store, endpointIRI, "${ssoNS}has-value", prop, "${xsdNS}double")
        rdf.addDataProperty(store, endpointIRI, "${ssoNS}has-unit", units)
      }
    }
  }

}

if (ui.fileExists(logFilename)) ui.remove(logFilename)
logfile = ui.newFile(logFilename, logMessages )
ui.open(logfile)

if (ui.fileExists(outputFilename)) ui.remove(outputFilename)
output = ui.newFile(outputFilename, rdf.asTurtle(store) )
// ui.open(output)

println "Materials: $materialCounter"
println "Assays: $assayCount"
println "  of which TOX: $toxCount"
println "Citations: $citations"


