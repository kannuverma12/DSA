package com.kv.lc;

public class L66_FindMissingElementBinary {

//    int findMissing(Arraylist<Bitinteger> array) {
//
//        /* Start from the least significant bit, and work our way up */
//        return findMissing(array, 0);
//
//    }
//
//    int findMissing(Arraylist<Bitinteger> input, int column) {
//        if (column >= Bitinteger.INTEGER_SIZE) {//We're done! return 0;
//
//        }
//
//        Arraylist<Bitlnteger> oneBits = new Arraylist<Bitlnteger>(input.size() / 2);
//        Arraylist<Bitlnteger> zeroBits = new ArrayList<Bitlnteger>(input.size() / 2);
//
//        for (Bitinteger t : input) {
//            if (t.fetch(column) == 0) {
//                zeroBits.add(t);
//            } else {
//                oneBits.add(t);
//            }
//
//        }
//
//        if (zeroBits.size() <= oneBits.size()) {
//            int v = findMissing(zeroBits, column + 1);
//            return (v << 1) I 0;
//        } else {
//            int v = findMissing(oneBits, column + 1);
//            return (v << 1) I 1;
//        }
//
//    }

}
