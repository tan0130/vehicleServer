package testDeom;

import database.Tb_history_message;

public class Tb_history_messageTest {

	public static void main(String[] args) {
		String currentTime = "23 23 02 FE 4C 41 39 35 43 38 36 48 30 46 31 4C 43 30 30 30 31 01 02 25 11 07 18 0D 09 1C 01 01 03 01 00 00 00 01 83 B9 15 B8 27 39 5B 01 00 0C 04 00 0002 01 01 01 53 4E 20 4E 20 64 15 E0 27 10 05 00 06 D0 F5 BD 01 5A 0A 00 06 01 A1 0C FB 01 6E 0C CC 01 46 4A 01 1D 44 07 03 00 00 00 01 00 00 00 00 08 01 01 15"+ 
				"B8 27 39 00 A8 00 01 A8 0C F5 0C F8 0C F8 0C F7 0C F5 0C F4 0C F3 0C F4 0C F5 0C F8 0C F8 0C F7 0C F8 0C F0 0C ED 0C ED 0C EE 0C EB 0C ED 0C EB 0C EE 0C ED 0C "+
				"EB 0C F5 0C F9 0C F9 0C F9 0C F9 0C F9 0C F4 0C F5 0C F8 0C F8 0C F7 0C F9 0C F9 0C F5 0C F7 0C F8 0C F9 0C F8 0C F7 0C F7 0C F0 0C F0 0C F8 0C F7 0C F9 0C F8 "+
				"0C F7 0C EE 0C EE 0C ED 0C EE 0C EE 0C F0 0C F0 0C ED 0C EE 0C EE 0C F1 0C F1 0C EE 0C F0 0C F1 0C F1 0C F0 0C F0 0C F1 0C ED 0C F7 0C F8 0C F9 0C F5 0C F9 0C"+
				"FA 0C F2 0C F4 0C F7 0C F8 0C F9 0C F7 0C F9 0C F5 0C F4 0C F8 0C F7 0C F8 0C F8 0C F8 0C EF 0C F3 0C F8 0C F7 0C F9 0C F8 0C F8 0C F4 0C F4 0C F6 0C F6 0C F6 "+
				"0C F7 0C F4 0C F6 0C F4 0C F3 0C F4 0C F0 0C CC 0C F0 0C F1 0C F0 0C F0 0C F0 0C F0 0C F0 0C F7 0C F8 0C F5 0C F9 0C F8 0C F7 0C F3 0C F3 0C F8 0C F8 0C F8 0C "+
				"F8 0C F7 0C F5 0C F7 0C F9 0C F8 0C F8 0C F8 0C F8 0C F2 0C F3 0C F8 0C F8 0C F7 0C F8 0C F8 0C F7 0C F3 0C F0 0C F0 0C EE 0C F0 0C E5 0C EE 0C F0 0C F1 0C EE "+
				"0C F8 0C F9 0C F8 0C F9 0C F8 0C FB 0C F2 0C F7 0C F8 0C F7 0C F8 0C F8 0C F5 09 01 01 00 78 47 46 45 45 46 47 47 49 49 47 46 46 46 47 47 47 46 45 45 45 46 46 "+
				"46 46 46 46 45 45 44 45 46 46 45 45 47 47 46 45 45 45 46 46 46 47 46 46 46 46 47 47 49 48 47 46 46 46 46 47 47 48 48 47 47 46 46 46 46 46 48 4A 46 46 46 46 46 "+
				"46 47 46 45 46 46 46 46 46 46 45 45 45 46 46 45 45 46 45 46 45 45 44 46 46 46 46 47 46 47 46 45 46 47 47 47 47 46 45 45 47 46 47 48 48 34";
	
		String currentTime2 = "23 23 02 FE 4C 43 30 36 53 32 34 53 33 48 30 39 39 31 33 32 32 01 01 61 11 09 0D 0C 15 3A 01 01 03 01 00 00 00 01 19 81 12 48 27 B3 25 01 0E 05 68 00 00 02 02 01 04 52 4E 20 4E 20 64 00 00 00 00 02 01 4F 4E 20 4E 20 64 00 00 00 00 05 00 06 FE 9A DB 01 F6 7F 77 06 01 00 FF FF 01 00 FF FF 01 00 FF 01 00 FF 07 00 00 00 00 00 00 00 00 00 08 01 01 12 48 27 B3 01 20 00 C9 58 0C CA 0C C2 0C C6 0C C5 0C C7 0C C7 0C C6 0C C3 0C C5 0C C5 0C C4 0C C9 0C CA 0C C6 0C C0 0C C7 0C C1 0C BF 0C C0 0C C1 0C C1 0C C1 0C C2 0C BF 0C BF 0C BE 0C BE 0C BF 0C BE 0C C0 0C BA 0C BE 0C BE 0C BB 0C B9 0C BE 0C C0 0C BE 0C C0 0C C0 0C BD 0C BE 0C BD 0C BD 0C BF 0C BC 0C BD 0C BE 0C BC 0C BF 0C BD 0C BD 0C BD 0C BD 0C BC 0C BE 0C BE 0C BC 0C BD 0C BE 0C BD 0C BD 0C BE 0C BE 0C BE 0C BF 0C BC 0C BD 0C BC 0C BD 0C BA 0C BD 0C BB 0C BC 0C BE 0C BE 0C BD 0C BD 0C BA 0C BC 0C BB 0C BD 0C BC 0C BD 0C BC 0C BC 0C BC 0C BC 09 01 01 00 48 46 45 46 46 45 46 47 45 47 47 45 47 46 45 46 45 44 46 45 43 45 44 42 44 44 42 44 44 42 44 44 42 44 44 42 44 47 46 47 46 45 46 46 45 46 46 45 46 46 45 46 46 45 47 45 43 45 45 43 45 45 43 45 45 43 45 45 43 45 45 43 45 9F ";
		Tb_history_message ta = new Tb_history_message();
		ta.analysis(currentTime);
//		ta.insert();
//		Tb_history_message ta2 = new Tb_history_message();
//		ta2.select();
		System.out.println("Tb_history_messageTest执行完毕");
		
	}

}
