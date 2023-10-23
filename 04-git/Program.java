public class KeyMatrix {
    private final byte[][] bytes;
    private final RCon rcon;

    public KeyMatrix(byte[][] input)
    {
        bytes = input;
        rcon = new RCon((byte) 1);
    }
    public KeyMatrix(byte[][] input, RCon newRcon)
    {
        bytes = input;
        rcon = newRcon;
    }
    public byte[][] Key()
    {
        return bytes;
    }
    public KeyMatrix NextMatrix()
    {
        byte[][] result = new byte[4][4];
        byte[] first = new Row(bytes[3]).ShiftedLeft(1);  //Take the last row from previous key and rotate it.
        for(int i = 0; i < 4; i++)  //Second step of processing the first row is SBox substitution.
        {
            first[i] = new EncodedByte(first[i]).SBoxValue();
        }
        first[0] ^= rcon.Value();   //Third step is XORing the first byte of first row by the RCon value.
        for(int i = 0; i < 4; i++)  //And now we XOR it with first row of previous key to get the first row of new matrix.
        {
            result[0][i] = (byte) (first[i]^bytes[0][i]);
        }

            //Remaining rows are easier to do.
        for(int y = 1; y < 4; y++)
        {
            for(int i = 0; i < 4; i++)
            {
                result[y][i] = (byte) (result[y-1][i]^bytes[y][i]);
            }
        }

        return new KeyMatrix(result, rcon.Next());
    }
}