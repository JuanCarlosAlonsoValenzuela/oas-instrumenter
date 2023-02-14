## Table of contents

1. [Added invariants](#added-invariants)
1. [Disabled invariants](#disabled-invariants)
1. [Enabled invariants](#enabled-invariants)
1. [Changes in computeConfidence functions](#changes-in-computeconfidence-functions)
1. [Suppression of derived variables](#suppression-of-derived-variables)
1. [Invariants disabled in the default Daikon configuration](#invariants-disabled-in-the-default-daikon-configuration)

This section describes the changes performed in Daikon version 5.8.10 (released on November 2021)

Section [Invariants disabled in the default Daikon configuration](#invariants-disabled-in-the-default-daikon-configuration) contains the 13 invariants that we disabled in the default Daikon configuration to avoid a combinatorial explosions
of string comparisons and a high number of false positives.


## Added invariants

1. **IsUrl:** Indicates that the value of a string variable is always a URL. Represented as `x is Url`.

1. **SequenceStringElementsAreUrl:** Indicates that all elements of an array of strings are URLs. Represented
as `All the elements of x are URLs`.

1. **FixedLengthString:** Indicates that the value of a string variable always has a fixed length n. Represented
as `LENGTH(x)==n`.

1. **SequenceFixedLengthString:** Indicates that all the elements of an array of strings have a fixed length n.
Represented as `All the elements of x have LENGTH=n`.

1. **IsNumeric:** Indicates that the characters of a string variable are always numeric. Represented as `x is
Numeric`.

1. **SequenceStringElementsAreNumeric:** Indicates that the characters of all the elements of an array of
strings are always numeric. Represented as `All the elements of x are Numeric`.

1. **IsEmail:** Indicates that the value of a string type variable is always an email. Represented as `x is Email`.

1. **SequenceStringElementsAreEmail:** Indicates that all elements of an array of strings are emails.
Represented as `All the elements of x are emails`.

1. **IsDateDDMMYYYY:** Indicates that the value of a string variable is always a date following the format
DD/MM/YYYY (the separator can be “/” or “-“). Represented as `x is a Date. Format: DD/MM/YYYY`.

1. **SequenceStringElementsAreDateDDMMYYYY:** Indicates that all the elements of an array of strings are
dates following the format DD/MM/YYYY. Represented as `All the elements of x are dates`.
Format: DD/MM/YYYY.

1. **IsDateMMDDYYYY:** Indicates that the value of a string variable is always a date following the format
MM/DD/YYYY (the separator can be “/” or “-“). Represented as `x is a Date. Format: MM/DD/YYYY`.

1. **SequenceStringElementsAreDateMMDDYYYY:** Indicates that all the elements of an array of strings are
dates following the format MM/DD/YYYY. Represented as `All the elements of x are dates`.
Format: MM/DD/YYYY.

1. **IsDateYYYYMMDD:** Indicates that the value of a string variable is always a date following the format
YYYY/MM/DD (the separator can be “/” or “-“). Represented as `x is a Date. Format: YYYY/MM/DD`.

1. **SequenceStringElementsAreDateYYYYMMDD:** Indicates that all the elements of an array of strings are
dates following the format YYYY/MM/DD. Represented as `All the elements of x are dates.
Format: YYYY/MM/DD`.

1. **IsHour:** Indicates that the value of a string variable is always a time in 24-hour format. Represented as `x
is Hour: HH:MM 24-hour format, optional leading 0`.

1. **SequenceStringElementsAreHour:** Indicates that all elements of an array of strings are hours in 24-hour
format. Represented as `All the elements of x are Hours: HH:MM 24-hour format,
optional leading 0`.

1. **IsHourAMPM:** Indicates that the value of a string variable is always a time in 12-hour format. Represented
as `x is Hour: HH:MM 12-hour format, optional leading 0`.

1. **SequenceStringElementsAreHourAMPM:** Indicates that all elements of an array of strings are hours in
12-hour format. Represented as `All the elements of x are Hours: HH:MM 12-hour format,
optional leading 0, mandatory meridiems (AM/PM)`.

1. **IsHourWithSeconds:** Indicates that the value of a string variable is always a time in 24-hour format,
including seconds. Represented as `x is Hour: HH:MM:SS 24-hour format with optional
leading 0`.

1. **SequenceStringElementsAreHourWithSeconds:** Indicates that all elements of an array of strings are hours
in 24-hour format, including seconds. Represented as `All the elements of x are Hours:
HH:MM:SS 24-hour format with optional leading 0`.

1. **IsTimestampYYYYMMHHThhmmssmm:** Indicates that the value of a string variable is always a timestamp.
Represented as x is Timestamp. Format: `YYYY-MM-DDTHH:MM:SS.mmZ (Miliseconds are
optional)`.

1. **SequenceStringElementsAreTimestampYYYYMMHHThhmmssmm:** Indicates that all elements of an array
of strings are timestamps. Represented as All the elements of x are Timestamps. Format:
   `YYYY-MM-DDTHH:MM:SS.mmZ (Miliseconds are optional)`.

## Disabled invariants
Section [5.5 Invariant list](https://plse.cs.washington.edu/daikon/download/doc/daikon.html#Invariant-list) of the Daikon user manual contains a description of each one of these invariants.

1. **daikon.inv.unary.scalar.NonZero**
1. **daikon.inv.unary.scalar.NonZeroFloat**
1. **daikon.inv.unary.scalar.RangeInt.PowerOfTwo**
1. **daikon.inv.unary.sequence.EltNonZero**
1. **daikon.inv.unary.sequence.EltNonZeroFloat**
1. **daikon.inv.unary.sequence.EltRangeInt.PowerOfTwo**
1. **daikon.inv.binary.twoScalar.IntNonEqual**
1. **daikon.inv.binary.twoScalar.FloatNonEqual**
1. **daikon.inv.binary.twoScalar.LinearBinary**
1. **daikon.inv.binary.twoScalar.LinearBinaryFloat**
1. **daikon.inv.binary.twoString.StringNonEqual**
1. **daikon.inv.binary.twoString.StringLessThan**
1. **daikon.inv.binary.twoString.StringGreaterThan**
1. **daikon.inv.binary.twoString.StringLessEqual**
1. **daikon.inv.binary.twoString.StringGreaterEqual**
1. **daikon.inv.binary.twoSequence.SeqSeqStringEqual**
1. **daikon.inv.binary.twoSequence.SeqSeqStringLessThan**
1. **daikon.inv.binary.twoSequence.SeqSeqStringGreaterThan**
1. **daikon.inv.binary.twoSequence.SeqSeqStringLessEqual**
1. **daikon.inv.binary.twoSequence.SeqSeqStringGreaterEqual**
1. **daikon.inv.binary.twoSequence.PairwiseStringLessThan**
1. **daikon.inv.binary.twoSequence.PairwiseStringGreaterThan**
1. **daikon.inv.binary.twoSequence.PairwiseStringLessEqual**
1. **daikon.inv.binary.twoSequence.PairwiseStringGreaterEqual**
1. **daikon.inv.ternary.threeScalar.LinearTernary**
1. **daikon.inv.ternary.threeScalar.LinearTernaryFloat**
1. **daikon.inv.binary.twoScalar.NumericInt.Divides**
1. **daikon.inv.binary.twoScalar.NumericInt.Square**
1. **daikon.inv.binary.twoScalar.NumericFloat.Divides**
1. **daikon.inv.binary.twoScalar.NumericFloat.Square**
1. **daikon.inv.binary.twoSequence.PairwiseNumericInt.Divides**
1. **daikon.inv.binary.twoSequence.PairwiseNumericInt.Square**
1. **daikon.inv.binary.twoSequence.PairwiseNumericFloat.Divides**
1. **daikon.inv.binary.twoSequence.PairwiseNumericFloat.Square**
1. **daikon.inv.binary.twoSequence.PairwiseLinearBinary**
1. **daikon.inv.binary.twoSequence.PairwiseLinearBinaryFloat**

## Enabled invariants
Section [5.5 Invariant list](https://plse.cs.washington.edu/daikon/download/doc/daikon.html#Invariant-list) of the Daikon user manual contains a description of each one of these invariants.

1. **daikon.inv.binary.twoString.StdString.SubString:** We applied two modifications on this invariant:
   - We added a property that sets a minimum string length (2 by default) in order to avoid reporting that a string that is always empty is always a substring of every other string in the program point.

   - This invariant may report redundant information that would bloat our results, for example, if x is a substring of y and y is a substring of z, Daikon would report the invariants: “x is substring of y”, “y is substring of z” and “x is substring of z”. In this situation we consider the “x is substring of z” invariant redundant and modified Daikon so it would not report redundant invariants, in order to avoid bloating the results. 

1. **daikon.inv.binary.twoSequence.SubSequence**
1. **daikon.inv.binary.twoSequence.SubSequenceFloat**
1. **daikon.inv.binary.twoSequence.SubSet**
1. **daikon.inv.binary.twoSequence.SubSetFloat**
1. **daikon.inv.binary.twoSequence.SuperSequence**
1. **daikon.inv.binary.twoSequence.SuperSequenceFloat**
1. **daikon.inv.binary.twoSequence.SuperSet**
1. **daikon.inv.binary.twoSequence.SuperSetFloat** 

## Changes in computeConfidence functions
At the end of the Daikon execution, the `computeConfidence` function is executed for every invariant that has not been falsified, returning a number between 0 and 1. 
If this number achieves a certain threshold, the invariant is considered to be statistically justified and it is reported to the user. We found some invariants for which
Daikon always returned a confidence of 1.

```
protected double computeConfidence() {
   return CONFIDENCE_JUSTIFIED;
}
```

We modified these functions to return 1 only if there was at least one occurrence of the invariant during the program execution.

```
protected double computeConfidence() {
    if (ppt.num_samples() == 0) {
      return CONFIDENCE_UNJUSTIFIED;
    }
return CONFIDENCE_JUSTIFIED;
}
```

These are the invariants for which we modified the `computeConfidence` function:

* Unary:
  - Scalar:
     - RangeFloat
     - RangeInt
  - Sequence:
    - EltRangeFloat
    - EltRangeInt
* Binary:
  - SequenceString:
    - MemberString
  - SequenceScalar:
    - Member
    - MemberFloat
  - TwoSequence:
    - Reverse
  - ReverseFloat
    - SubSequence
    - SubSequenceFloat
    - Subset 

## Suppression of derived variables

A derived variable is an expression that does not appear directly in the instrumented program, 
but which Daikon uses as a variable for invariant detection. 
For example, for array variables, Daikon generates a derived variable whose value is its size, 
allowing to detect invariants such as `input.limit >= size(return.items)`.

However, there are derived variables that do not provide any relevant information, this is the case of the derived variable 
`orig()`, which indicates the value of a variable at the input program point and is used to make comparisons with 
the value of the same variable at the output program point. In the context of black box testing of RESTful APIs, the value 
of the input parameters does not change at any time, so Daikon would only detect equality invariants between the parameter 
value at the input and output (`input.limit == orig(input.limit)`) that will always be satisfied. 
For this reason, this derived variable has been disabled.

In some cases, Daikon applies the `shift` derived variable (consisting on decreasing the value of a numerical variable by a shift value)
to try to find relations between numerical variables (e.g., `input-1 > return`), we disabled this derived variable because it would most likely
report misleading information.

## Invariants disabled in the default Daikon configuration
Section [5.5 Invariant list](https://plse.cs.washington.edu/daikon/download/doc/daikon.html#Invariant-list) of the Daikon user manual contains a description of each one of these invariants.

1. **daikon.inv.binary.twoString.StringNonEqual**
1. **daikon.inv.binary.twoString.StringLessThan**
1. **daikon.inv.binary.twoString.StringGreaterThan**
1. **daikon.inv.binary.twoString.StringLessEqual**
1. **daikon.inv.binary.twoString.StringGreaterEqual**
1. **daikon.inv.binary.twoSequence.SeqSeqStringLessThan**
1. **daikon.inv.binary.twoSequence.SeqSeqStringGreaterThan**
1. **daikon.inv.binary.twoSequence.SeqSeqStringLessEqual**
1. **daikon.inv.binary.twoSequence.SeqSeqStringGreaterEqual**
1. **daikon.inv.binary.twoSequence.PairwiseStringLessThan**
1. **daikon.inv.binary.twoSequence.PairwiseStringGreaterThan**
1. **daikon.inv.binary.twoSequence.PairwiseStringLessEqual**
1. **daikon.inv.binary.twoSequence.PairwiseStringGreaterEqual**