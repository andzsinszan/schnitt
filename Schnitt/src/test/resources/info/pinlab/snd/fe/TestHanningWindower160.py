#! coding: utf-8


############
# Create test files for HannigWindower(samples: 160, hz = 16000, winLen = 10ms)
############

import Fe
import ConfigParser
import sys
import os

# paramter
wav_file = "test_10ms.wav"
window_len = 10
windowtype = "Hanning"
hz = 16000

# filename for output
output_int_file = "test_int_160_wav.txt"
output_windowed_file = "TestHanningWindower160.txt"


if __name__ == "__main__":
    fe = Fe.Fe()
    fe.set_wavfile(wav_file)        \
        .set_windowtype(windowtype) \
        .set_hz(hz)                 \
        .set_windowlen(window_len)  \


    # read int value of wav samples
    fe.do_wav2int()
    int_samples = fe.get_intsamples()
    #print "int_samples", len(int_samples)
    windowed_samples = fe.do_windowning(int_samples)


    #print windowed_samples
    f = open(output_int_file, "w")
    for i in range(0, len(int_samples)):
        f.write(str(int_samples[i]) + "\n")
    f.close()


    f = open(output_windowed_file, "w")
    for i in range(0, len(windowed_samples)):
        f.write(str(windowed_samples[i]) + "\n")
    f.close()

    print "finish: ", __file__, "\n", "window length: ", window_len, "\n", "input file: ", wav_file, "\n", "output file: ", output_windowed_file
