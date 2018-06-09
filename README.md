# GenomicBox GUI

## Introduction

**This java GUI is currently at an early stage of development**. 
I have worked mainly on the graphical interface. The next step is to connect 
it to the different pipelines that I am developing. This interface is 
developed under Ubuntu. I have not yet developed for graphical uniformity 
display for MAC OS and Windows.

The focus of this interface is on the treatment and analysis of ChIP-seq and 
RNA-seq data.

## Manifesto

Research in genomics often needs standard analysis to enable a first 
investigation of the data. This often involves very often meta-gene profiling 
with ChIP-seq, rough peak detection, or differential expression on annotated 
genes with RNA-seq. Moreover, obtaining bigwigs to visualize the results of an 
experiment is an absolute priority for the biologist. Waiting for the 
availability of a bioinformatician to have *this first look* can be frustrating
 for the biologist, and be a burden for the bioinformatician being overloaded 
with requests.

This point is exemplified by Milicchio et al, Visual programming for 
next-generation sequencing data analytics, BioData Mining, 2016:

*Most of the current NGS software requires dedicated bioinformaticians with 
access to comprehensive computational infrastructure. Just a few years ago, 
there was a bottleneck between data generation and inference (analyzing and 
making sense of the data), but nowadays, access to these bioinformatics
resources is more common and affordable. The new bottleneck is the evolution of
 software inaccordance with technological advances and users needs.*

Several tools aim at providing solutions to accomplish standard 
bioinformatic analysis with graphical interfaces (EaSeq, QuickNGS, Galaxy, 
etc). However, I noticed that biologists who are not familiar with 
computational tools or are new to genome-wide analysis face conceptual 
challenges that typically require a large amount of time to understand the 
different parameters proposed by the majority of tools. Moreover, regarding the
 large success of IGV among biologists, I decided to provide the 
software in Java hoping to facilitate the adoption of GenomicBox by the 
community in the same manner. The interfaces aim at being *minimalistic* in an 
effort of simplification.

This interface aims at providing **an extremely minimalistic** number of 
parameters to the biologists while performing data processing and analysis in 
enumerative approaches. In other words, the power of bioinformatic tools 
will be fully exploited by generating maximum results using different 
combinations of parameters. The advantage of such an approach is that one can 
see the effects of the parameters in a visual way, enabling the user to focus 
on the biology. 
The interface is dynamic, hiding or displaying options according to the user 
selection, hence providing a guidance through the analysis. One of the future 
developments will be to provide step-by-step guided interfaces for each kind of 
analysis. 

## Structure

This documentation will expand with the project. Below is a screenshot of the 
interface for DE analysis (the tables are completed automatically upon file 
selection) and a general overview of the different analysis provided by the 
GUI:

![Example of interface to perform Differential Expression Analysis](./images/interface.png)

![Structure of the interface](./images/structure.png)
