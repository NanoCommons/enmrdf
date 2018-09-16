# ENMRDF Collection

Collection of Turtle files with RDF in the standard eNanoMapper format.

Pull requests welcome, questions too.

Copyright/License/Waiver: [CCZero](https://creativecommons.org/choose/zero/)


## RDF Structures

In the below examples, the following general prefixes are used:

```turtle
@prefix void:  <http://rdfs.org/ns/void#> .
@prefix owl:   <http://www.w3.org/2002/07/owl#> .
@prefix enm:   <http://purl.enanomapper.org/onto/> .
@prefix xsd:   <http://www.w3.org/2001/XMLSchema#> .
@prefix rdfs:  <http://www.w3.org/2000/01/rdf-schema#> .
@prefix npo:   <http://purl.bioontology.org/ontology/npo#> .
@prefix sso:   <http://semanticscience.org/resource/> .
@prefix bao:   <http://www.bioassayontology.org/bao#> .
@prefix dct:   <http://purl.org/dc/terms/> .
@prefix rdf:   <http://www.w3.org/1999/02/22-rdf-syntax-ns#> .
@prefix etox:  <http://egonw.github.com/enmrdf/nanoe-tox/> .
@prefix obo:   <http://purl.obolibrary.org/obo/> .
@prefix dc:    <http://purl.org/dc/elements/1.1/> .
```

### A dataset 

A dataset is of type `void:DataSet` and has a title at this moment.

```turtle
etox:dataset  a    void:DataSet ;
    dct:title    "NanoE-Tox RDF" .
    dct:description  "RDF version of the data from Beilstein J. Nanotechnol. 2015, 6, 1788–1804. doi:10.3762/bjnano.6.183." ;
    dct:license      <https://creativecommons.org/licenses/by/4.0/> ;
    dct:publisher    "Egon Willighagen" .
```

### A material

```turtle
ex:NFYS16-M12  a     obo:CHEBI_59999 ;
    rdfs:label       "NM-400" ;
    npo:has_part     ex:NFYS16-M12_core ;
    obo:BFO_0000056  ex:NFYS16-M12_sizemg ;
    dcterms:source   ex:NFYS16 ;
    dcterms:type     enm:ENM_9000081 .
```

All materials are types (rdf:type) [obo:CHEBI_59999](http://bioportal.bioontology.org/ontologies/ENM/?p=classes&conceptid=http%3A%2F%2Fpurl.obolibrary.org%2Fobo%2FCHEBI_59999&jump_to_nav=true) and have a name (rdfs:label).
Structure information is linked via the npo:has_part predicate and experimental
measurements (physchem, toxicity, eco, application) is linked via the
`obo:BFO_0000056` predicate. A classification of the nanomaterial can be provided
with the `dcterms:type` predicate and an entry from the [eNanoMapper ontology](http://bioportal.bioontology.org/ontologies/ENM/).

#### The chemical composition

The chemical composition of the nanomaterial is encoded by linking material components
to the nanomaterial, as was done in the above nanomaterial description with the
`npo:has_part` predicate. The components itself all need a unique IRI and
are encoded like this:

```turtle
ex:NFYS16-M12_core
    a                      npo:NPO_1597 ;
    sso:CHEMINF_000200     ex:NFYS16-M12_core_smiles .

ex:NFYS16-M12_core_smiles
    a               sso:CHEMINF_000018 ;
    sso:SIO_000300  "[C]" .
```

## Example SPARQL queries

Get links out to Sigma-Aldrich:

```sparql
PREFIX foaf: <http://xmlns.com/foaf/0.1/>

SELECT ?enmMaterial ?linkout WHERE {
  ?enmMaterial foaf:page ?linkout .
  FILTER (REGEX(STR(?linkout), "sigmaaldrich.com"))
}
```

And similarly to get owl:sameAs links:

```sparql
PREFIX owl: <http://www.w3.org/2002/07/owl#>

CONSTRUCT {
  ?source owl:sameAs ?linkout
} WHERE {
  ?source owl:sameAs ?linkout .
  FILTER (REGEX(STR(?linkout), "doi.org"))
}
```
