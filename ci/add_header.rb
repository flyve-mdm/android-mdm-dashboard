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

# Add header to all files on the folder development/coverage
Dir.glob("development/coverage/**/*.html") do |search_file| # note one extra "*"
    file = File.open("#{search_file}", "r+")
    buffer = file.read
    file.rewind
    file.puts "---"
    file.puts "layout: coverage"
    file.puts "---"
    file.print buffer
    file.close

    # rename folder resources
    data = File.read("#{search_file}")
    filtered_data = data.gsub(".resources", "resources")
    File.open("#{search_file}", "w") {|file| file.puts filtered_data }

    # rename .session.html
    data = File.read("#{search_file}")
    filtered_data = data.gsub(".session", "session")
    File.open("#{search_file}", "w") {|file| file.puts filtered_data }

end

# Add header to all files on the folder developmenttest-reports
Dir.glob("development/test-report/**/*.html") do |search_file| # note one extra "*"
    file = File.open("#{search_file}", "r+")
    buffer = file.read
    file.rewind
    file.puts "---"
    file.puts "layout: coverage"
    file.puts "---"
    file.print buffer
    file.close
end

# Add header to all files on the folder development/code-documentation
Dir.glob("development/code-documentation/**/*.html") do |search_file| # note one extra "*"
    file = File.open("#{search_file}", "r+")
    buffer = file.read
    file.rewind
    file.puts "---"
    file.puts "layout: codeDocumentation"
    file.puts "---"
    file.print buffer
    file.close
end