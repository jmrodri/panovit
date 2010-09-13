GROUP = "panovit"
COPYRIGHT = "(c) 2009 jesus m. rodriguez"

#############################################################################
# DEPENDENCIES
#############################################################################

TIVOHME  = ['tivo.hme:hme:jar:1.4.1', 'tivo:bananas:jar:1.3.1', 'tivo.hme:simulator:jar:1.4.1', 'tivo.hme:hme-host-sample:jar:1.4.1']

JUNIT = ['junit:junit:jar:4.5', 'org.mockito:mockito-all:jar:1.8.5']

EXPECTJ = ['net.sourceforge.expectj:expectj:jar:2.0.1', 'commons-logging:commons-logging:jar:1.1.1']

GSTREAMER = ['org.gstreamer:gstreamer-java:jar:1.4']

localurl = "file:///usr/share/maven2/repository/JPP/jna-3.2.7.jar"
JNA = download(artifact('jna:jna:jar:3.2.7')=>localurl)
#############################################################################
## REPOSITORIES
##
## Specify Maven 2.0 remote repositories here, like this:
repositories.remote << "http://jmrodri.fedorapeople.org/ivy/panovit"
repositories.remote << "http://www.ibiblio.org/maven2"

desc "Pandora TiVo application"
define "panovit" do
  #
  # project info
  #
  project.version = '0.0.1'
  project.group = 'panovit'
  manifest["Implementation-Vendor"] = COPYRIGHT

  #
  # eclipse settings
  # http://buildr.apache.org/more_stuff.html#eclipse
  #
  eclipse.natures 'org.eclipse.jdt.core.javanature'
  eclipse.builders 'org.eclipse.jdt.core.javabuilder'

  #
  # building
  #
  compile.options.target = '1.6'
  compile.with TIVOHME, EXPECTJ, GSTREAMER, JNA

  #
  # testing
  #
  test.with TIVOHME, JUNIT, EXPECTJ, GSTREAMER, JNA

  package(:jar)

end
# runs the eclipse task to generate the .classpath and .project
# # files, then fixes the output.
task :eclipse do
  puts "Fixing eclipse .classpath"
  text = File.read(".classpath")
  tmp = File.new("tmp", "w")
  tmp.write(text.gsub(/output="target\/resources"/, ""))
  tmp.close()
  FileUtils.copy("tmp", ".classpath")
  File.delete("tmp")

  # make the gettext output dir to silence eclipse errors
  mkdir_p("target/generated-source")
end
