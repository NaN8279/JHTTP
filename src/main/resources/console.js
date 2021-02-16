function Console() {
    this.counts = {"default": 0};

    this.count = function(label) {
        countToPrint = 1;
        if (counts[label] != null) {
            countToPrint = counts[label];
        }
        java.lang.System.out.println(label + ": " + counts[label]);
    };

    this.countReset = function(label) {
        counts[label] = 0;
    };

    this.log = function(message) {
        java.lang.System.out.println(message);
    };
}

var console = new Console();