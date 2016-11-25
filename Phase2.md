# Phase 2

This phase builds on phase 1 to add fix code based on Findbugs.

* Ran Findbugs via Eclipse plugin (although the maven plugin would work the same).
* Added finbugs reporting plugin to pom.
* Created an exception file for excluding certain findings.

## Pre-requisites
* Phases 1 and 1.5

## Usage

1. Run ```mvn findbugs:findbugs```.
1. Or run ```mvn site```.
