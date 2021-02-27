package kz.pompei.electro_schema.bin;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

public class BinTest {

  @Test
  public void bigEndian() {
    Bin bin = Bin.bigEndian();
    assertThat(bin.seq).isEqualTo(BinSeq.BigEndian);
  }

  @Test
  public void littleEndian() {
    Bin bin = Bin.littleEndian();
    assertThat(bin.seq).isEqualTo(BinSeq.LittleEndian);
  }

  @DataProvider
  private Object[][] allEndian() {
    return Arrays.stream(BinSeq.values())
                 .map(x -> new Object[]{x})
                 .toArray(Object[][]::new);
  }

  ///
  ///
  ///
  /// int
  ///
  ///
  ///

  @Test(dataProvider = "allEndian")
  public void readInt__byte0(BinSeq seq) {
    Bin bin = new Bin(seq);

    byte[] buffer = seq == BinSeq.LittleEndian
      ? new byte[]{1, 0, 0, 0}
      : new byte[]{0, 0, 0, 1};

    //
    //
    int value = bin.readInt(buffer, 0);
    //
    //

    assertThat(value).isEqualTo(1);
  }

  @Test(dataProvider = "allEndian")
  public void readInt__byte1(BinSeq seq) {
    Bin bin = new Bin(seq);

    byte[] buffer = seq == BinSeq.LittleEndian
      ? new byte[]{0, 7, 0, 0}
      : new byte[]{0, 0, 7, 0};

    //
    //
    int value = bin.readInt(buffer, 0);
    //
    //

    assertThat(value).isEqualTo(7 * 256);
  }

  @Test(dataProvider = "allEndian")
  public void readInt__byte2(BinSeq seq) {
    Bin bin = new Bin(seq);

    byte[] buffer = seq == BinSeq.LittleEndian
      ? new byte[]{0, 0, 17, 0}
      : new byte[]{0, 17, 0, 0};

    //
    //
    int value = bin.readInt(buffer, 0);
    //
    //

    assertThat(value).isEqualTo(17 * 256 * 256);
  }

  @Test(dataProvider = "allEndian")
  public void readInt__byte3(BinSeq seq) {
    Bin bin = new Bin(seq);

    byte[] buffer = seq == BinSeq.LittleEndian
      ? new byte[]{0, 0, 0, 13}
      : new byte[]{13, 0, 0, 0};

    //
    //
    int value = bin.readInt(buffer, 0);
    //
    //

    assertThat(value).isEqualTo(13 * 256 * 256 * 256);
  }

  private final Random random = new Random();

  @Test(dataProvider = "allEndian", invocationCount = 3)
  public void writeInt(BinSeq seq) {
    Bin bin = new Bin(seq);

    byte[] buffer = new byte[4];
    random.nextBytes(buffer);

    int value = bin.readInt(buffer, 0);

    byte[] actual = new byte[4];

    //
    //
    bin.writeInt(actual, 0, value);
    //
    //

    assertThat(actual).isEqualTo(buffer);
  }

  ///
  ///
  ///
  /// long
  ///
  ///
  ///

  @Test(dataProvider = "allEndian")
  public void readLong__byte0(BinSeq seq) {
    Bin bin = new Bin(seq);

    byte[] buffer = seq == BinSeq.LittleEndian
      ? new byte[]{1, 0, 0, 0, 0, 0, 0, 0}
      : new byte[]{0, 0, 0, 0, 0, 0, 0, 1};

    //
    //
    long value = bin.readLong(buffer, 0);
    //
    //

    assertThat(value).isEqualTo(1);
  }

  @Test(dataProvider = "allEndian")
  public void readLong__byte1(BinSeq seq) {
    Bin bin = new Bin(seq);

    byte[] buffer = seq == BinSeq.LittleEndian
      ? new byte[]{0, 7, 0, 0, 0, 0, 0, 0}
      : new byte[]{0, 0, 0, 0, 0, 0, 7, 0};

    //
    //
    long value = bin.readLong(buffer, 0);
    //
    //

    assertThat(value).isEqualTo(7L * 256L);
  }

  @Test(dataProvider = "allEndian")
  public void readLong__byte2(BinSeq seq) {
    Bin bin = new Bin(seq);

    byte[] buffer = seq == BinSeq.LittleEndian
      ? new byte[]{0, 0, 17, 0, 0, 0, 0, 0}
      : new byte[]{0, 0, 0, 0, 0, 17, 0, 0};

    //
    //
    long value = bin.readLong(buffer, 0);
    //
    //

    assertThat(value).isEqualTo(17L * 256L * 256L);
  }

  @Test(dataProvider = "allEndian")
  public void readLong__byte3(BinSeq seq) {
    Bin bin = new Bin(seq);

    byte[] buffer = seq == BinSeq.LittleEndian
      ? new byte[]{0, 0, 0, 13, 0, 0, 0, 0}
      : new byte[]{0, 0, 0, 0, 13, 0, 0, 0};

    //
    //
    long value = bin.readLong(buffer, 0);
    //
    //

    assertThat(value).isEqualTo(13L * 256L * 256L * 256L);
  }

  @Test(dataProvider = "allEndian", invocationCount = 3)
  public void writeLong(BinSeq seq) {
    Bin bin = new Bin(seq);

    byte[] buffer = new byte[8];
    random.nextBytes(buffer);

    long value = bin.readLong(buffer, 0);

    byte[] actual = new byte[8];

    //
    //
    bin.writeLong(actual, 0, value);
    //
    //

    assertThat(actual).isEqualTo(buffer);
  }

}