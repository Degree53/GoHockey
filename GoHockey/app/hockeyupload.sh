#!/bin/sh

APK_PATH=$1
REVISION=$2
FROM_REVISION=$3
APPID=$4
HOCKEYTOKEN=$5

echo "Getting Git commit message"

if [ "$FROM_REVISION" == "$REVISION" ]; then
	echo 'No from revision'
	COMMIT_MESSAGE=`git log -5 --oneline $REVISION`
else
	echo 'Using from revision'
	COMMIT_MESSAGE=`git log --oneline $FROM_REVISION..$REVISION`
fi

echo $COMMIT_MESSAGE

curl \
	-F "status=2" \
	-F "notify=1" \
	-F "notes=$COMMIT_MESSAGE" \
	-F "notes_type=0" \
	-F "ipa=@$APK_PATH" \
	-H "X-HockeyAppToken: $HOCKEYTOKEN" \
	https://rink.hockeyapp.net/api/2/apps/$APPID/app_versions/upload

echo $?