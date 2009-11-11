// Mini - simple jav
a card applet for demonstration
// File: Mini.java
//
// Source code based on java card specification version 2.1.
// Compiled with JBuilder 6 and JDK 1.3.1. Tested with Java Card Application Studio
// (JCAST) V 2.2 and UniverSIM Java Card 64 kB from Giesecke & Devrient.
//
// Package AID: 'D2 76 00 00 60 50 01'
// Applet AID:  'D2 76 00 00 60 41 01'
//
// This source code is under GNU general public license (see www.opensource.org for details).
// Please send corrections and ideas for extensions to Wolfgang Rankl (www.wrankl.de)
// Copyright 2003 by Wolfgang Rankl, Munich
//
// 20. Nov. 2003 - V 3: correct wrong cast for Lc calculation, 1st published version
// 17. Nov. 2003 - V 2: returncode 6Cxx adopted, improved documentation
// 13. Nov. 2003 - V 1: initial runnable version
//---------------------------------------------------------------------------------------

package ro.bmocanu.tests.evaluate.javacard;               // this is the package name
import javacard.framework.*;    // import all neccessary packages for java card

public class Mini extends Applet {
  // this are the constants
  final static byte CLASS     = (byte) 0x80;  // Class of the APDU commands
  final static byte INS_READ  = (byte) 0x02;  // instruction for the READ APDU command

  // this is the text string which will send back from the ICC to the IFD
  final static byte[] text  = {(byte) 'e', (byte) 't', (byte) 's', (byte) ' ',
    (byte) 'g', (byte) 'e', (byte) 'h', (byte) 't', (byte) 's', (byte) ' ',
    (byte) 'd', (byte) 'e', (byte) 's', (byte) ' ',
    (byte) 'G', (byte) 'l', (byte) 'u', (byte) 'm', (byte) 'p'};

  // this is the install method which will be called once for installation
  // and registration of the applet
  public static void install(byte[] buffer, short offset, byte length) {
    new Mini().register();
  }  // install

  // this is the method, which will be called from JCRE
  public void process(APDU apdu) {
    byte[] cmd_apdu = apdu.getBuffer();           // the cmd_apdu variable is used because of performance reasons

    if (cmd_apdu[ISO7816.OFFSET_CLA] == CLASS) {  // it is the rigth class
      switch(cmd_apdu[ISO7816.OFFSET_INS]) {      // check the instruction byte
        case INS_READ:                            // it is a READ instruction
          // check if P1=P2=0
          if ((cmd_apdu[ISO7816.OFFSET_P1] != 0) || (cmd_apdu[ISO7816.OFFSET_P2] != 0)) {
            ISOException.throwIt(ISO7816.SW_WRONG_P1P2);
          }  // if
          // check if P3=length of the text field
          short le = (short)(cmd_apdu[ISO7816.OFFSET_LC] & 0x00FF);  // calculate Le (expected length)
          short len_text = (short)text.length;                       // the len_text variable is used because of performance reasons
          if (le != len_text) {
            ISOException.throwIt((short)(ISO7816.SW_CORRECT_LENGTH_00 + len_text));  // give the expected length back to the IFD
          }  // if
          apdu.setOutgoing();                           // set transmission to outgoing data
          apdu.setOutgoingLength((short)len_text);      // set the number of bytes to send to the IFD
          apdu.sendBytesLong(text, (short)0, (short)len_text);
          break;
        default :  // the instruction in the command apdu is not supported
          ISOException.throwIt(ISO7816.SW_INS_NOT_SUPPORTED);
      }  // switch
    }  // if
    else {         // the class in the command apdu is not supported
      ISOException.throwIt(ISO7816.SW_CLA_NOT_SUPPORTED);
    }  // else
  }  // process
}  // class