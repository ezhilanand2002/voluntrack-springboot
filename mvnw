#!/bin/sh
# ----------------------------------------------------------------------------
# Maven Start Up shell script for Unix/Linux/macOS
# ----------------------------------------------------------------------------

PRG="$0"
while [ -h "$PRG" ]; do
  ls=`ls -ld "$PRG"`
  link=`sed -e 's/.*-> //' <<EOF
$ls
EOF
`
  if expr "$link" : '/.*' > /dev/null; then
    PRG="$link"
  else
    PRG=`dirname "$PRG"`/"$link"
  fi
done
SAVED=`pwd`
CDPATH=
export CDPATH
cd "`dirname "$PRG"`/" >/dev/null
MAVEN_PROJECTBASEDIR=`pwd`
cd "$SAVED" >/dev/null

WRAPPER_JAR="$MAVEN_PROJECTBASEDIR/.mvn/wrapper/maven-wrapper.jar"
WRAPPER_URL="https://repo.maven.apache.org/maven2/org/apache/maven/wrapper/maven-wrapper/3.2.0/maven-wrapper-3.2.0.jar"

if [ ! -f "$WRAPPER_JAR" ]; then
    mkdir -p "$MAVEN_PROJECTBASEDIR/.mvn/wrapper"
    if command -v curl >/dev/null 2>&1; then
        curl -fsSL "$WRAPPER_URL" -o "$WRAPPER_JAR"
    elif command -v wget >/dev/null 2>&1; then
        wget -q "$WRAPPER_URL" -O "$WRAPPER_JAR"
    fi
fi

exec java -jar "$WRAPPER_JAR" "$@"
