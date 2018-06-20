#!/usr/bin/env bash
#
#  Copyright (C) 2017 Teclib'
#
#  This file is part of Flyve MDM.
#
#  Flyve MDM Admin Dashboard Android is a subproject of Flyve MDM. Flyve MDM is a mobile
#  device management software.
#
#  Flyve MDM Android is free software: you can redistribute it and/or
#  modify it under the terms of the GNU General Public License
#  as published by the Free Software Foundation; either version 3
#  of the License, or (at your option) any later version.
#
#  Flyve MDM Inventory Agent Android is distributed in the hope that it will be useful,
#  but WITHOUT ANY WARRANTY; without even the implied warranty of
#  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
#  GNU General Public License for more details.
#  ------------------------------------------------------------------------------
#  @author    Rafael Hernandez - rafaelje
#  @copyright Copyright (c) 2017 Flyve MDM
#  @license   GPLv3 https://www.gnu.org/licenses/gpl-3.0.html
#  @link      https://github.com/flyve-mdm/flyve-mdm-android-admin-dashboard
#  @link      http://www.glpi-project.org/
#  @link      https://flyve-mdm.com/
#  ------------------------------------------------------------------------------
#

GH_COMMIT_MESSAGE=$(git log --pretty=oneline -n 1 $CIRCLE_SHA1)

if [[ $GH_COMMIT_MESSAGE != *"ci(release): generate CHANGELOG.md for version"* && $GH_COMMIT_MESSAGE != *"build(properties): add new properties values"* && $GH_COMMIT_MESSAGE != *"ci(release): update version on android manifest"* ]]; then

# run generate documentation script
./ci/scripts/ci_generate_documentation.sh

# get gh-pages branch
git fetch origin gh-pages

# move to gh-pages
git checkout gh-pages

sudo git clean -fdx

# remove default stylesheet.css
sudo rm ./development/code-documentation/stylesheet.css

# remove css
sudo rm ./development/coverage/resources/report.css
sudo rm ./development/test-reports/css/base-style.css
sudo rm ./development/test-reports/css/style.css

# add new css
cp ./css/codeDocumentation.css ./development/code-documentation/stylesheet.css

# add new css
cp ./css/coverage.css ./development/coverage/resources/report.css
cp ./css/testReports.css ./development/test-reports/css/style.css
touch ./development/test-reports/css/base-style.css

# change headers
ruby ci/add_header.rb

# git add
git add . && git commit -m "docs(development): update headers and css styles"

# push to branch
git push origin gh-pages

# got back to original branch
git checkout $CIRCLE_BRANCH

fi