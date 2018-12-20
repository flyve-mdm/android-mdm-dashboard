#!/usr/bin/env bash
#
#  LICENSE
#
#  This file is part of Flyve MDM Inventory Agent for Android.
#
#  Inevntory Agent for Android is a subproject of Flyve MDM. 
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
#  ---------------------------------------------------------------------
#  @copyright Copyright © 2018 Teclib. All rights reserved.
#  @license   GPLv3 https://www.gnu.org/licenses/gpl-3.0.html
#  @link      https://github.com/flyve-mdm/android-inventory-agent/
#  @link      http://flyve.org/android-inventory-agent/
#  @link      https://flyve-mdm.com/
#  ---------------------------------------------------------------------
#

# Since we will download a video, we require integrity checking with CRC32c
# But the crcmod installation in the docker image isn't using the module's C extension
# So, uninstall it and install again with the C extension
echo "y" | sudo pip uninstall crcmod

sudo pip install -U crcmod

# create json key file
echo $GCLOUD_SERVICE_KEY | base64 --decode --ignore-garbage > ${HOME}/gcloud-service-key.json

# activate the account
gcloud auth activate-service-account --key-file ${HOME}/gcloud-service-key.json

# config the project
gcloud config set project ${GCLOUD_PROJECT}

# Run Instrumented test
gcloud firebase test android run \
  --type instrumentation \
  --app $(ls -dt ~/flyve_mdm/app/build/outputs/apk/debug/*.apk | head -1) \
  --test $(ls -dt ~/flyve_mdm/app/build/outputs/apk/androidTest/debug/*.apk | head -1) \
  --device model=Nexus6,version=25,locale=en,orientation=portrait  \
  --timeout 90s