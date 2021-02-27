package kz.pompei.electro_schema.bin;

public class Bin {
  public final BinSeq seq;

  public Bin(BinSeq seq) {
    this.seq = seq;
  }

  public static Bin bigEndian() {
    return new Bin(BinSeq.BigEndian);
  }

  public static Bin littleEndian() {
    return new Bin(BinSeq.LittleEndian);
  }

  public int readInt(byte[] buffer, int offset) {
    return switch (seq) {
      case LittleEndian -> 0
        | ((((int) buffer[offset + 0]) & 0xFF) << (8 * 0))
        | ((((int) buffer[offset + 1]) & 0xFF) << (8 * 1))
        | ((((int) buffer[offset + 2]) & 0xFF) << (8 * 2))
        | ((((int) buffer[offset + 3]) & 0xFF) << (8 * 3));
      case BigEndian -> 0
        | ((((int) buffer[offset + 3]) & 0xFF) << (8 * 0))
        | ((((int) buffer[offset + 2]) & 0xFF) << (8 * 1))
        | ((((int) buffer[offset + 1]) & 0xFF) << (8 * 2))
        | ((((int) buffer[offset + 0]) & 0xFF) << (8 * 3));
    };
  }

  @SuppressWarnings("DuplicatedCode")
  public void writeInt(byte[] buffer, int offset, int value) {

    switch (seq) {
      case LittleEndian -> {
        buffer[offset + 0] = (byte) ((value >> (8 * 0)) & 0xFF);
        buffer[offset + 1] = (byte) ((value >> (8 * 1)) & 0xFF);
        buffer[offset + 2] = (byte) ((value >> (8 * 2)) & 0xFF);
        buffer[offset + 3] = (byte) ((value >> (8 * 3)) & 0xFF);
      }
      case BigEndian -> {
        buffer[offset + 3] = (byte) ((value >> (8 * 0)) & 0xFF);
        buffer[offset + 2] = (byte) ((value >> (8 * 1)) & 0xFF);
        buffer[offset + 1] = (byte) ((value >> (8 * 2)) & 0xFF);
        buffer[offset + 0] = (byte) ((value >> (8 * 3)) & 0xFF);
      }
      default -> throw new RuntimeException("FBoMR64uFw :: Unknown " + seq);
    }
  }

  public long readLong(byte[] buffer, int offset) {
    return switch (seq) {
      case LittleEndian -> 0
        | ((((long) buffer[offset + 0]) & 0xFF) << (8 * 0))
        | ((((long) buffer[offset + 1]) & 0xFF) << (8 * 1))
        | ((((long) buffer[offset + 2]) & 0xFF) << (8 * 2))
        | ((((long) buffer[offset + 3]) & 0xFF) << (8 * 3))
        | ((((long) buffer[offset + 4]) & 0xFF) << (8 * 4))
        | ((((long) buffer[offset + 5]) & 0xFF) << (8 * 5))
        | ((((long) buffer[offset + 6]) & 0xFF) << (8 * 6))
        | ((((long) buffer[offset + 7]) & 0xFF) << (8 * 7))
      ;
      case BigEndian -> 0
        | ((((long) buffer[offset + 7]) & 0xFF) << (8 * 0))
        | ((((long) buffer[offset + 6]) & 0xFF) << (8 * 1))
        | ((((long) buffer[offset + 5]) & 0xFF) << (8 * 2))
        | ((((long) buffer[offset + 4]) & 0xFF) << (8 * 3))
        | ((((long) buffer[offset + 3]) & 0xFF) << (8 * 4))
        | ((((long) buffer[offset + 2]) & 0xFF) << (8 * 5))
        | ((((long) buffer[offset + 1]) & 0xFF) << (8 * 6))
        | ((((long) buffer[offset + 0]) & 0xFF) << (8 * 7))
      ;
    };
  }

  @SuppressWarnings("DuplicatedCode")
  public void writeLong(byte[] buffer, int offset, long value) {
    switch (seq) {
      case LittleEndian -> {
        buffer[offset + 0] = (byte) ((value >> (8 * 0)) & 0xFF);
        buffer[offset + 1] = (byte) ((value >> (8 * 1)) & 0xFF);
        buffer[offset + 2] = (byte) ((value >> (8 * 2)) & 0xFF);
        buffer[offset + 3] = (byte) ((value >> (8 * 3)) & 0xFF);
        buffer[offset + 4] = (byte) ((value >> (8 * 4)) & 0xFF);
        buffer[offset + 5] = (byte) ((value >> (8 * 5)) & 0xFF);
        buffer[offset + 6] = (byte) ((value >> (8 * 6)) & 0xFF);
        buffer[offset + 7] = (byte) ((value >> (8 * 7)) & 0xFF);
      }
      case BigEndian -> {
        buffer[offset + 7] = (byte) ((value >> (8 * 0)) & 0xFF);
        buffer[offset + 6] = (byte) ((value >> (8 * 1)) & 0xFF);
        buffer[offset + 5] = (byte) ((value >> (8 * 2)) & 0xFF);
        buffer[offset + 4] = (byte) ((value >> (8 * 3)) & 0xFF);
        buffer[offset + 3] = (byte) ((value >> (8 * 4)) & 0xFF);
        buffer[offset + 2] = (byte) ((value >> (8 * 5)) & 0xFF);
        buffer[offset + 1] = (byte) ((value >> (8 * 6)) & 0xFF);
        buffer[offset + 0] = (byte) ((value >> (8 * 7)) & 0xFF);
      }
      default -> throw new RuntimeException("FBoMR64uFw :: Unknown " + seq);
    }
  }

}
