#!/usr/bin/env bash
#
#  LICENSE
# 
#  This file is part of Flyve MDM Admin Dashboard for Android.
#
#  Admin Dashboard for Android is a subproject of Flyve MDM.
#  Flyve MDM is a mobile device management software.
# 
#  Flyve MDM is free software: you can redistribute it and/or
#  modify it under the terms of the GNU General Public License
#  as published by the Free Software Foundation; either version 3
#  of the License, or (at your option) any later version.
#
#  Flyve MDM is distributed in the hope that it will be useful,
#  but WITHOUT ANY WARRANTY; without even the implied warranty of
#  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
#  GNU General Public License for more details.
#  -------------------------------------------------------------------
#  @author    Rafael Hernandez - <rhernandez@teclib.com>
#  @author    Naylin Medina    - <nmedina@teclib.com>
#  @copyright Copyright Teclib. All rights reserved.
#  @license   GPLv3 https://www.gnu.org/licenses/gpl-3.0.html
#  @link      https://github.com/flyve-mdm/android-mdm-dashboard/
#  @link      http://flyve.org/android-mdm-dashboard/
#  @link      https://flyve-mdm.com
#  -------------------------------------------------------------------
#

COMMIT_MESSAGE=$(git log --pretty=oneline -n 1 $CIRCLE_SHA1)

# only update list when documentation is updated
if [[ $COMMIT_MESSAGE == *"ci(development): update documentation"* && -z "$CIRCLE_PULL_REQUEST" ]]; then

# check if support folder exists
if [ -d "development/code-documentation/support" ]; then

# remove list to create a new one and not duplicate folders
rm ./_data/whitelist_version.yml

# create fresh list
touch ./_data/whitelist_version.yml

# set path to directory where the versions folders are
FOLDER_PATH="development/code-documentation/support"

# get folders in release directory
DIRS=`ls $FOLDER_PATH`

# add version folders to list
for DIR in $DIRS
do
echo  - ${DIR} >> ./_data/whitelist_version.yml
done
# if the list has changed commit and push changes
  if [ -n "$(git status --porcelain _data/whitelist_version.yml)" ]; then

    echo "Updating version list"

    # configure git
    git config --global user.email "apps@teclib.com"
    git config --global user.name "Teclib' bot"

    # add new remote to push changes
    git remote remove origin
    git remote add origin https://$GITHUB_USER:$GITHUB_TOKEN@github.com/$CIRCLE_PROJECT_USERNAME/$CIRCLE_PROJECT_REPONAME.git

    git add _data/whitelist_version.yml && git commit -m "ci(list): update version list"
    git push origin gh-pages
  fi
fi

fi