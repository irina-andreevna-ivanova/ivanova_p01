call mvn install:install-file -Dfile=org.dyno.visual.swing.borders_0.9.12.I20090506-2220.jar -DgroupId=org.dyno.visual.swing -DartifactId=visual-swing-borders -Dversion=0.9.12 -Dpackaging=jar
call mvn install:install-file -Dfile=org.dyno.visual.swing.grouplayouts_0.9.12.I20090506-2220.jar -DgroupId=org.dyno.visual.swing -DartifactId=visual-swing-grouplayouts -Dversion=0.9.12 -Dpackaging=jar
call mvn install:install-file -Dfile=org.dyno.visual.swing.layouts_0.9.12.I20090527-2200.jar -DgroupId=org.dyno.visual.swing -DartifactId=visual-swing-layouts -Dversion=0.9.12 -Dpackaging=jar
call mvn install:install-file -Dfile=org.dyno.visual.swing.lnfs_0.9.12.I20090506-2220.jar -DgroupId=org.dyno.visual.swing -DartifactId=visual-swing-lnfs -Dversion=0.9.12 -Dpackaging=jar
call mvn install:install-file -Dfile=org.dyno.visual.swing.types_0.9.12.I20090527-2200.jar -DgroupId=org.dyno.visual.swing -DartifactId=visual-swing-types -Dversion=0.9.12 -Dpackaging=jar
call mvn install:install-file -Dfile=org.dyno.visual.swing_0.9.12.I20090527-2200.jar -DgroupId=org.dyno.visual.swing -DartifactId=visual-swing -Dversion=0.9.12 -Dpackaging=jar

@rem substance Look and Feel
call mvn install:install-file -Dfile=substance.jar -DgroupId=org.jvnet.substance -DartifactId=substance -Dversion=5.3 -Dpackaging=jar
