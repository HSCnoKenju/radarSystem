
#!/bin/sh

## see the whole pin mapping
## pin 25 is phisical 22 and Wpi 6
## gpio use Wpi standard
# gpio readall

gpio mode 6 out #
gpio write 6 0 # 0 is off
