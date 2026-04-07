class Robot {
    private int width;
    private int height;
    private int perimeter;
    private int pos;
    private boolean hasMoved;

    public Robot(int width, int height) {
        this.width = width;
        this.height = height;
        this.perimeter = 2 * (width - 1) + 2 * (height - 1);
        this.pos = 0;
        this.hasMoved = false;
    }
    
    public void step(int num) {
        pos = (pos + num) % perimeter;
        if (num > 0) {
            hasMoved = true;
        }
    }
    
    public int[] getPos() {
        if (pos == 0) return new int[]{0, 0};
        
        
        if (pos <= width - 1) {
            return new int[]{pos, 0};
        }        
        else if (pos <= width - 1 + height - 1) {
            return new int[]{width - 1, pos - (width - 1)};
        }        
        else if (pos <= 2 * (width - 1) + height - 1) {
            return new int[]{width - 1 - (pos - (width - 1 + height - 1)), height - 1};
        }
        else {
            return new int[]{0, height - 1 - (pos - (2 * (width - 1) + height - 1))};
        }
    }
    
    public String getDir() {
        if (pos == 0) {
            return hasMoved ? "South" : "East";
        }
        
        if (pos <= width - 1) {return "East";}
        
        if (pos <= width - 1 + height - 1) {return "North";}
        
        if (pos <= 2 * (width - 1) + height - 1) {return "West";}
        
        return "South";
    }
}
