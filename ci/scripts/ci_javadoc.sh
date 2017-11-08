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
# Generate javadoc this folder must be on .gitignore
javadoc -d ./reports/javadoc -sourcepath ./app/src/main/java -subpackages . -nonavbar

# delete the index.html file
sudo rm ./reports/javadoc/index.html

# rename the overview-summary.html file toindex.html
mv ./reports/javadoc/overview-summary.html ./reports/javadoc/index.html

# add reports
git add reports -f

# create commit with temporary report folder
git commit -m "tmp report commit"

# get gh-pages branch
git fetch origin gh-pages

# move to gh-pages
git checkout gh-pages

# delete old javadoc folder
sudo rm -R reports/javadoc

# get fresh javadoc folder
git checkout $CIRCLE_BRANCH reports/javadoc

# remove default stylesheet.css
sudo rm ./reports/javadoc/stylesheet.css

# add new css
cp ./css/javadoc.css ./reports/javadoc/stylesheet.css

# git add javadoc folder
git add reports/javadoc

# git add
git add ./reports/javadoc/stylesheet.css

# create commit for documentation
git commit -m "docs(javadoc): update javadoc"

# change headers
ruby ci/add_header.rb

# git add
git add .

# git commit
git commit -m "docs(headers): update headers"

# push to branch
git push origin gh-pages

# got back to original branch
git checkout $CIRCLE_BRANCH