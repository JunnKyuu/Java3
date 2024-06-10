package symbolTable;

public class Symbol {
    private String name;
    private int size;
    private String type;
    private int address;

    public Symbol(String name, int size, String type, int address) {
        this.name = name;
        this.size = size;
        this.type = type;
        this.address = address;
    }

    public int getSize() { return size; }
    public String getName() { return name; }
    public String getType() { return type; }
    public int getAddress() { return address; }

    @Override
    public String toString() {
        return "{name: " + name + ", " +
        		"size: " + size + ", " + 
        		"type: " + type + ", " + 
        		"address: ds + " + address + "}";
    }
}
