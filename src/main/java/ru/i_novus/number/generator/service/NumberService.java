package ru.i_novus.number.generator.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.i_novus.number.generator.repository.NumberRepository;
import ru.i_novus.number.generator.model.Number;

import java.util.*;

@Component
public class NumberService {

    private static final Logger logger = LoggerFactory.getLogger(NumberService.class);

    private final char[] c = {'А', 'Е', 'Т', 'О', 'Р', 'Н', 'У', 'К', 'Х', 'С', 'В', 'М'};
    private final Random random = new Random();
    private char[] lastChars = new char[3];
    private int[] lastNums = new int[3];

    @Autowired
    NumberRepository numberRepository;

    public NumberService() {
        Arrays.sort(this.c);
        this.InitComponents();
    }

    private void InitComponents() {
        Arrays.fill(this.lastNums, 0);
        Arrays.fill(this.lastChars, this.c[0]);
    }

    public String next() {
        this.Increment();
        while (this.ExistsInBD()) {
            this.Increment();
        }
        this.SaveToDB(this.GetCurrentFullNumber());
        return this.GetCurrentFullNumber();
    }

    public String prev() {
        this.Decrement();
        while (this.ExistsInBD()) {
            this.Decrement();
        }
        this.SaveToDB(this.GetCurrentFullNumber());
        return this.GetCurrentFullNumber();
    }

    public String random() {

        String result = "";

        logger.info(result);

        this.randomUtil();
        while (this.ExistsInBD()) {
            this.randomUtil();
        }

        result = GetCurrentFullNumber();
        this.SaveToDB(result);
        return result;
    }

    public boolean ExistsInBD(){
        return numberRepository.existsByNumber(this.GetCurrentFullNumber());
    }

    // Return was in database before save attempt
    private boolean SaveToDB(String number) {
        if (!this.ExistsInBD()) {
            numberRepository.save(new Number(number));
            return false;
        } else {
            return true;
        }
    }

    // Should be private if isn't used in debugging
    public String GetCurrentFullNumber() {
        return GetCurrentIncompleteNumber() + " 116 RUS";
    }

    private void randomUtil() {
        for (byte i = 0; i < 3; i++) {
            this.lastChars[i] = this.RandomChar();
        }
        for (byte i = 0; i < 3; i++) {
            this.lastNums[i] = this.random.nextInt(0, 10);
        }
    }

    private String GetCurrentIncompleteNumber() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.lastChars[0]);
        for(int i = 0; i < this.lastNums.length; i++){
            sb.append(this.lastNums[i]);
        }
        for (byte i = 1; i < 3; i++) {
            sb.append(this.lastChars[i]);
        }
        return sb.toString();
    }

    private char RandomChar() {
        return c[this.random.nextInt(0, this.c.length - 1)];
    }

    private void Increment() {
        if (this.NumIncrement()) {
            this.CharIncrement();
        }
    }

    private void Decrement() {
        if (this.NumDecrement()) {
            this.CharDecrement();
        }
    }

    private boolean NumDecrement() {
        boolean needToDecNext = true;
        boolean needCharDec = false;
        int k = this.lastNums.length;
        while (needToDecNext) {
            k--;
            if (k != 0) {
                if (this.lastNums[k] == 0) {
                    this.lastNums[k] = 9;
                } else {
                    this.lastNums[k]--;
                    needToDecNext = false;
                }
            } else {
                if (this.lastNums[k] == 0) {
                    for (int i = 0; i < this.lastNums.length; i++) {
                        this.lastNums[k] = 9;
                    }
                    needCharDec = true;
                } else {
                    this.lastNums[k]--;
                }
                needToDecNext = false;
            }
        }
        if (this.AreNumsZeroes(this.lastNums)) {
            Arrays.fill(this.lastNums, 9);
            needCharDec = true;
        }
        return needCharDec;
    }

    private boolean AreNumsZeroes(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                return false;
            }
        }
        return true;
    }

    private boolean NumIncrement() {
        boolean needToIncNext = true;
        boolean needCharInc = false;
        int k = this.lastNums.length;
        while (needToIncNext) {
            k--;
            if (k != 0) {
                if (this.lastNums[k] == 9) {
                    this.lastNums[k] = 0;
                } else {
                    this.lastNums[k]++;
                    needToIncNext = false;
                }
            } else {
                if (this.lastNums[k] == 9) {
                    Arrays.fill(this.lastNums, 0);
                    this.lastNums[this.lastNums.length - 1] = 1;
                    needCharInc = true;
                } else {
                    this.lastNums[k]++;
                }
                needToIncNext = false;
            }
        }

        return needCharInc;
    }

    private void CharDecrement() {
        boolean needToDecNext = true;
        int k = this.lastChars.length;
        while (needToDecNext) {
            k--;
            if (k != 0) {
                if (this.lastChars[k] == this.c[0]) {
                    this.lastChars[k] = this.c[this.c.length - 1];
                } else {
                    this.lastChars[k] = this.c[this.FindPositionInCharArray(this.lastChars[k], this.c) - 1];
                    needToDecNext = false;
                }
            } else {
                if (this.lastChars[k] == this.c[0]) {
                    Arrays.fill(this.lastChars, this.c[this.c.length - 1]);
                } else {
                    this.lastChars[k] = this.c[this.FindPositionInCharArray(this.lastChars[k], this.c) - 1];
                }
                needToDecNext = false;
            }
        }
    }

    private void CharIncrement() {
        boolean needToIncNext = true;
        int k = this.lastChars.length;
        while (needToIncNext) {
            k--;
            if (k != 0) {
                if (this.lastChars[k] == this.c[this.c.length - 1]) {
                    this.lastChars[k] = this.c[0];
                } else {
                    this.lastChars[k] = this.c[this.FindPositionInCharArray(this.lastChars[k], this.c) + 1];
                    needToIncNext = false;
                }
            } else {
                if (this.lastChars[k] == this.c[this.c.length - 1]) {
                    Arrays.fill(this.lastChars, this.c[0]);
                } else {
                    this.lastChars[k] = this.c[this.FindPositionInCharArray(this.lastChars[k], this.c) + 1];
                }
                needToIncNext = false;
            }
        }
    }

    private int FindPositionInCharArray(char c, char[] cs) {
        int result = 0;

        for (int i = 0; i < cs.length; i++) {
            if (cs[i] == c) {
                result = i;
                break;
            }
        }

        return result;
    }

    // Debug method
    public String restart() {
        this.InitComponents();
        this.lastNums[this.lastNums.length - 1] = 1;
        return this.GetCurrentFullNumber();
    }

    // Debug method
    public String silentNext() {
        this.Increment();
        return this.GetCurrentFullNumber();
    }

    // Debug method
    public String silentPrev() {
        this.Decrement();
        return this.GetCurrentFullNumber();
    }

    // Debug method
    public String SaveCurrentNumberToDB() {
        boolean wasIn = this.SaveToDB(this.GetCurrentFullNumber());
        String s = this.GetCurrentFullNumber();
        if (wasIn) {
            s += " was in DB.";
        } else {
            s += " wasn't in DB.";
        }
        return s;
    }

    // Debug method
    public String setNumsToEnd() {
        Arrays.fill(this.lastNums, 9);
        return this.GetCurrentFullNumber();
    }

    // Debug method
    public String setCharsToEnd() {
        Arrays.fill(this.lastChars, this.c[this.c.length - 1]);
        return this.GetCurrentFullNumber();
    }

    // Debug method
    public String setNumsToStart() {
        Arrays.fill(this.lastNums, 0);
        this.lastNums[this.lastNums.length - 1] = 1;
        return this.GetCurrentFullNumber();
    }

    // Debug method
    public String setCharsToStart() {
        Arrays.fill(this.lastChars, this.c[0]);
        return this.GetCurrentFullNumber();
    }

    // Debug method
    public String setToStart() {
        this.setCharsToStart();
        this.setNumsToStart();
        return this.GetCurrentFullNumber();
    }

    // Debug method
    public String setToEnd() {
        this.setCharsToEnd();
        this.setNumsToEnd();
        return this.GetCurrentFullNumber();
    }

}
