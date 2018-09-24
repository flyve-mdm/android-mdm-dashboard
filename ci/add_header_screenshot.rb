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

# add screenshots header
file = File.open("screenshots/index.html", "r+")
buffer = file.read
file.rewind
file.puts "---"
file.puts "layout: container"
file.puts "namePage: screenshots"
file.puts "---"
file.print buffer
file.close