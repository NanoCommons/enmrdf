Katre Juganson et al. have published a paper in BJON about NanoE-Tox. This database
was composed by the Laboratory of Environmental Toxicology of NICPB (http://www.kbfi.ee/).
The great news is that they released the content of this database under a
CC-BY 2.0 license as supplementary information of the article: 

http://doi.org/10.3762/bjnano.6.183

Conversion to ENMRDF
====================

This folder contains a Bioclipse script to convert the content from the database
into ENMRDF. The Excel spreadsheet was first adapted by removing all newlines
(global Find/Replace on '\n') and was then exported as a TSV file (no quoted field,
TAB separator). Of this file, four header rows were removed,
creating a TSV file without any headers and just data.

That file is the input of the script.
