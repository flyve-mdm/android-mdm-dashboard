/* ---------------------------------------------------------------------
*
*  LICENSE
*
*  This file is part of the GLPI API Client Library for Java,
*  a subproject of GLPI. GLPI is a free IT Asset Management.
*
*  GLPI is free software: you can redistribute it and/or
*  modify it under the terms of the GNU General Public License
*  as published by the Free Software Foundation; either version 3
*  of the License, or (at your option) any later version.
*
*  GLPI is distributed in the hope that it will be useful,
*  but WITHOUT ANY WARRANTY; without even the implied warranty of
*  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
*  GNU General Public License for more details.
*  --------------------------------------------------------------------
*  @author    Rafael Hernandez - <rhernandez@teclib.com>
*  @copyright (C) 2017 Teclib' and contributors.
*  @license   GPLv3 https://www.gnu.org/licenses/gpl-3.0.html
*  @link      https://github.com/glpi-project/java-library-glpi
*  @link      http://www.glpi-project.org/
*  --------------------------------------------------------------------
*/

package org.glpi.api.request;

import com.google.gson.annotations.SerializedName;

public class ResetPasswordRequest {

    @SerializedName("email")
    private String email;

    @SerializedName("password_forget_token")
    private String passwordForgetToken;

    @SerializedName("password")
    private String password;

    /**
     * Create a request for Recovery Password
     * @param string email
     * @param string passwordForgetToken
     * @param string password
     */
    public ResetPasswordRequest(String email, String passwordForgetToken, String password) {
        this.email = email;
        this.passwordForgetToken = passwordForgetToken;
        this.password = password;
    }

    /**
     * get Email
     * @return the email of the user to recover
     */
    public String getEmail() {
        return email;
    }

    /**
     * set Email
     * @param string the email of the user to recover
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * get Password Forget Token
     * @return the token of the forgotten password
     */
    public String getPasswordForgetToken() {
        return passwordForgetToken;
    }

    /**
     * set Password Forget Token
     * @param string the token of the forgotten password
     */
    public void setPasswordForgetToken(String passwordForgetToken) {
        this.passwordForgetToken = passwordForgetToken;
    }

    /**
     * get Password
     * @return the new password
     */
    public String getPassword() {
        return password;
    }

    /**
     * set Password
     * @param string the new password
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
