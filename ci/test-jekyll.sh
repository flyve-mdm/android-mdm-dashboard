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
#  @copyright Copyright Teclib. All rights reserved.
#  @license   GPLv3 https://www.gnu.org/licenses/gpl-3.0.html
#  @link      https://github.com/flyve-mdm/android-mdm-dashboard/
#  @link      http://flyve.org/android-mdm-dashboard/
#  @link      https://flyve-mdm.com
#  -------------------------------------------------------------------
#
set -e # halt script on error

bundle exec jekyll build
rm -rf _site/reports
rm -rf _site/screenshots
bundle exec htmlproofer ./_site --allow-hash-href true --assume-extension true --file-ignore ./_site/CHANGELOG.html