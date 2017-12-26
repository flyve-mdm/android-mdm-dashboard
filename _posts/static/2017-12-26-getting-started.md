---
layout: post
howtos: true
published: true
title: Getting started
permalink: howtos/getting-started
description: Welcome to Flyve MDM
---
# Dashboard

The management of your mobile fleet is through the Flyve MDM Dashboard which connects to the Agents on the devices of your fleet.

For this you require to have an active account in Flyve MDM, from which you will control remotely the devices of your fleet.

Here we'll guide you in the steps to manage your mobile fleet from the basics.

## Login

To create an account you only require an email address and fill the form, then you'll receive an email to validate your account.

Once this is done, Congratulations you are part of Flyve MDM!

You can now Login and take full control of your IT infrastructure.

## Adding devices

Go to the Devices section in your main screen, click the "+" button and add the email of the user whose device you'll manage.

Automatically, the user will receive an invitation in his email account, and now it will appear to you on the _Pending Invitations_ until it is accepted, helping you to keep track of the devices that already belong to your infrastructure.

Please note that the users must have installed in their devices the Android or iOS Agent, see these links for more information:

* [Android MDM Agent](http://flyve.org/android-mdm-agent/)
* [iOS MDM Agent](http://flyve.org/ios-mdm-agent/)

Once the user receives the email and enrolls you will now see it in the Device section, you can from there on access to the features, such as:

* Assign the device to a fleet
* Get a full System Report
* The applications installed
* Current location of the device

## Create your Fleet

You must create a new fleet since the "not managed fleet" is the default one and therefore comes locked.

In order to create it, you only have to click in the "+" button on the Fleet section, name it and add the [policies](#our-policies), applications and files your fleet requires.

Once you assign the devices to the Fleet, all the policies will be immediately applied!

## Our Policies

* Password: set the specifications as
  * Length
  * Quality
  * Minimun of letters (lowercase and uppercase)
  * Minimun of numbers
  * Wipe the device at a number of failed attempts to unlock the device
* Encryption of Internal Storage
* Peripherals: take control of
  * Cameras
  * Wifi
  * Bluetooth
  * GPS

## Adding Files & Applications

You must add in their respective sections the Files and Applications, in order use them when deploying your Fleet.

Go to the Files section, click on the "+" button, name and select your file, click on "add".

You can now select it when you use the Deploy file policy

The same procedure applies to the applications.

Go to the Applications section, click on the "+" button, name and select your application, click on "add"

And now you can select it when you use the Deploy application policy.
