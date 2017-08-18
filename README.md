ENMRDF Collection
=================

Collection of Turtle files with RDF in the standard eNanoMapper format.

Pull requests welcome, questions too.

Copyright/License/Waiver: [CCZero](https://creativecommons.org/choose/zero/)


RDF Structures
==============

A dataset:

```turtle
etox:dataset  a    void:DataSet ;
      dct:title    "NanoE-Tox RDF" .
```

A material:

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
obo:BFO_0000056 predicate. A classification of the nanomaterial can be provided
with the dcterms:type predicate and an entry from the [eNanoMapper ontology](http://bioportal.bioontology.org/ontologies/ENM/).

The components:

```turtle
ex:NFYS16-M12_core
    a                      npo:NPO_1597 ;
    sso:CHEMINF_000200     ex:NFYS16-M12_core_smiles .

ex:NFYS16-M12_core_smiles
    a               sso:CHEMINF_000018 ;
    sso:SIO_000300  "[C]" .
```

Example SPARQL queries
======================

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
