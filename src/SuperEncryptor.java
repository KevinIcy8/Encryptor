public class SuperEncryptor {
    private int shiftKey;
    private String[][] keyboard;

    public SuperEncryptor(int k){
        shiftKey = k;
        keyboard = new String[][]{{"Q","W","E","R","T","Y","U","I","O","P"},
                                    {"A","S","D","F","G","H","J","K","L"},
                                        {"Z","X","C","V","B","N","M"}};
    }


    public String shiftLetter(String character){
        int count = 0;
            for (int row = 0; row < keyboard.length; row++) {
                for (int col = 0; col < keyboard[row].length; col++) {

                    if (keyboard[row][col].equals(character)) {
                        System.out.print(character);
                        if (col + shiftKey > keyboard[row].length - 1) {
                            shiftKey = (col + shiftKey) - keyboard[row].length;
                            character = keyboard[row][shiftKey];
                            System.out.print(character + "1");
                        }
                        else {
                            character = keyboard[row][col + shiftKey];
                            System.out.print(character);
                        }
                        return character;
                    }
                }
            }
            System.out.println(count);
            return character;
    }

    public String superEncryptMessage(String message){
        StringBuilder encryption = new StringBuilder();
        System.out.println(message.length());
        for(int i = 0; i < message.length(); i++){
            String character = message.substring(i,i+1);
            character = character.toUpperCase();
            //System.out.print(shiftLetter(character));
            encryption.append(shiftLetter(character));
        }
        return encryption.toString();
    }
    public String superDecryptMessage(String message){

        return null;
    }
}
