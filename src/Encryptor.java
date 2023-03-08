public class Encryptor
{
    /** A two-dimensional array of single-character strings, instantiated in the constructor */
    private String[][] letterBlock;

    /** The number of rows of letterBlock, set by the constructor */
    private int numRows;

    /** The number of columns of letterBlock, set by the constructor */
    private int numCols;

    /** Constructor*/
    public Encryptor(int r, int c)
    {
        letterBlock = new String[r][c];
        numRows = r;
        numCols = c;
    }

    public String[][] getLetterBlock()
    {
        return letterBlock;
    }

    /** Places a string into letterBlock in row-major order.
     *
     *   @param str  the string to be processed
     *
     *   Postcondition:
     *     if str.length() < numRows * numCols, "A" in each unfilled cell
     *     if str.length() > numRows * numCols, trailing characters are ignored
     */
    public void fillBlock(String str)
    {
        int index = 0;
        if(str.length() < numRows * numCols){
            int extraSpace = (numRows * numCols) - str.length();
            for(int i = 0; i < extraSpace; i++){
                str = str + "A";
            }
        }
        for(int i = 0; i < numRows; i++){
            for(int j = 0; j < numCols; j++){
                letterBlock[i][j] = str.substring(index, index+1);
                index++;
            }
        }

    }

    /** Extracts encrypted string from letterBlock in column-major order.
     *
     *   Precondition: letterBlock has been filled
     *
     *   @return the encrypted string from letterBlock
     */
    public String encryptBlock()
    {
        String encrypted = "";
        for(int i = 0; i < numCols; i++){
            for(int j = 0; j < numRows; j++){
                encrypted = encrypted + letterBlock[j][i];
            }
        }
        return encrypted;
    }

    /** Encrypts a message.
     *
     *  @param message the string to be encrypted
     *
     *  @return the encrypted message; if message is the empty string, returns the empty string
     */
    public String encryptMessage(String message)
    {
        int numberOfArrays = (message.length()/(numRows * numCols));
        if((message.length()%(numRows * numCols)) != 0){
            numberOfArrays++;
        }
        String finalEncryption = "";
        for(int i = 0; i < numberOfArrays; i++){
            fillBlock(message);
            finalEncryption = finalEncryption + encryptBlock();
            if(message.length() > numRows * numCols) {
                message = message.substring(numRows * numCols);
            }
        }
        return finalEncryption;
    }

    /**  Decrypts an encrypted message. All filler 'A's that may have been
     *   added during encryption will be removed, so this assumes that the
     *   original message (BEFORE it was encrypted) did NOT end in a capital A!
     *
     *   NOTE! When you are decrypting an encrypted message,
     *         be sure that you have initialized your Encryptor object
     *         with the same row/column used to encrypted the message! (i.e.
     *         the “encryption key” that is necessary for successful decryption)
     *         This is outlined in the precondition below.
     *
     *   Precondition: the Encryptor object being used for decryption has been
     *                 initialized with the same number of rows and columns
     *                 as was used for the Encryptor object used for encryption.
     *
     *   @param encryptedMessage  the encrypted message to decrypt
     *
     *   @return  the decrypted, original message (which had been encrypted)
     *
     *   TIP: You are encouraged to create other helper methods as you see fit
     *        (e.g. a method to decrypt each section of the decrypted message,
     *         similar to how encryptBlock was used)
     */
    public String decryptMessage(String encryptedMessage)
    {
        String[][] decryption = new String[numRows][numCols];
        int index = 0;
        String originalMessage = "";
        int numberOfArrays = (encryptedMessage.length()/(numRows * numCols));
        if((encryptedMessage.length()%(numRows * numCols)) != 0){
            numberOfArrays++;
        }
        for(int k = 0; k < numberOfArrays; k++) {
            for (int i = 0; i < numCols; i++) {
                for (int j = 0; j < numRows; j++) {
                    decryption[j][i] = encryptedMessage.substring(index, index + 1);
                    index++;
                }
            }
            for (int i = 0; i < numRows; i++) {
                for (int j = 0; j < numCols; j++) {
                    originalMessage = originalMessage + decryption[i][j];
                }
            }
        }
        while(originalMessage.charAt(originalMessage.length() - 1) == 'A'){
            originalMessage = originalMessage.substring(0, originalMessage.length()-1);
        }
        return originalMessage;
    }
}