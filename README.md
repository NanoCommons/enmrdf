ENMRDF Collection
=================

Collection of Turtle files with RDF in the standard eNanoMapper format.

Pull requests welcome, questions too.

Copyright/License/Waiver: CCZero


RDF Structures
==============

A dataset:

    etox:dataset  a    void:DataSet ;
        dct:title  "NanoE-Tox RDF" .

A material:

    ex:NFYS16-M12  a         obo:CHEBI_59999 ;
        rdfs:label       "NM-400" ;
        npo:has_part     ex:NFYS16-M12_core ;
        obo:BFO_0000056  ex:NFYS16-M12_sizemg ;
        dcterms:source   etox:dataset ;
        dcterms:type     enm:ENM_9000081 .

All materials are types (rdf:type) obo:CHEBI_59999 and have a name (rdfs:label).
Structure information is linked via the npo:has_part predicate and experimental
measurements (physchem, toxicity, eco, application) is linked via the
obo:BFO_0000056 predicate. A classification of the nanomaterial can be provided
with the dcterms:type predicate and an entry from the eNanoMapper ontology.

The components:

    ex:NFYS16-M12_core
        a                      npo:NPO_1597 ;
        sso:CHEMINF_000200     ex:NFYS16-M12_core_smiles .

    ex:NFYS16-M12_core_smiles
        a               sso:CHEMINF_000018 ;
        sso:SIO_000300  "[C]" .

Example SPARQL queries
======================

Get links out to Sigma-Aldrich:

    prefix foaf: <http://xmlns.com/foaf/0.1/>
    
    select ?enmMaterial ?linkout where {
      ?enmMaterial foaf:page ?linkout .
      filter (regex(str(?linkout), "sigmaaldrich.com"))
    }

And similarly to get own:sameAs links:

    prefix owl: <http://www.w3.org/2002/07/owl#>
    
    construct {
      ?enmMaterial owl:sameAs ?linkout
    } where {
      ?enmMaterial owl:sameAs ?linkout .
      FILTER (regex(str(?linkout), "doi.org"))
    }
