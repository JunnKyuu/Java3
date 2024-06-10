package data;
public class CPU {
	
	// Association
	private Bus bus;
	
	public enum EDeviceId{
		eCpu,
		eMemory,
	}
	
	public enum EOpcode{
		eMove,
		eAdd,
		eAddC,
		eLoad,
		eLoadC,
		eStore,
		eHalt
	}
	
	public enum EStatus {
		// clear - 0XFE, set - 0X01, get - 모름
		eZero(0XFE, 0X01, 0X01 );
		
		private int nClear;
		private int nSet;
		private int nGet;
		
		private EStatus(int nClear, int nSet, int nGet) {
			this.nClear = nClear;
			this.nSet = nSet;
			this.nGet = nGet;
		}
		
		public int getNClear() {
			return this.nClear;
		}
		
		public int getNSet() {
			return this.nSet;
		}
		
		public int getNGet() {
			return this.nGet;
		}
	}
	
	enum ERegisters {
		eMAR,
		eMBR,
		ePC,
		eIR,
		eAC,
		eStatus
	}
	
	int registers[] = new int[ERegisters.values().length];
	
	public CPU() {
		
	}
	public void associate(Bus bus) {
		this.bus = bus;
	}
	public void initialize() {
		
	}
	
	private int get(ERegisters eRegister) {
		return registers[eRegister.ordinal()];
	}
	
	private int set(ERegisters eRegister, int value) {
		return registers[eRegister.ordinal()] = value;
	}
	
	private void setZero(boolean bResult) {
		if(bResult) {
			this.registers[ERegisters.eStatus.ordinal()]
					= this.registers[ERegisters.eStatus.ordinal()] & EStatus.eZero.getNClear();
		} else {
			this.registers[ERegisters.eStatus.ordinal()]
					= this.registers[ERegisters.eStatus.ordinal()] | EStatus.eZero.getNSet();
		}
	}
	
	private boolean getZero() {
		// ZeroFlag 반환
		// JMP할 때 사용
		// Constant로 따로 정리 enum으로
		
		// int로 바꿔서 1,0으로 바꿔도 됨
		return true;
	}
	
	private void setMinus(boolean bResult) {
		
	}
	
	// Instructions
	private void move(ERegisters eTarget, ERegisters eSourse) {
		registers[eTarget.ordinal()] = registers[eSourse.ordinal()]; 

	}
	
	// Instruction execution cycle
	private void fetch() {
		move(ERegisters.eMAR, ERegisters.ePC);
		set(ERegisters.eMBR, bus.load(EDeviceId.eMemory, get(ERegisters.eMAR)));
		move(ERegisters.eIR, ERegisters.eMBR);
	}
	
	private void decode() {
		int opCode = get(ERegisters.eIR) >> 24;
		if (opCode == EOpcode.eMove.ordinal()) {
			int operand1 = get(ERegisters.eIR) & 0x00FF0000;
			operand1 = operand1 >> 16;
			int operand2 = get(ERegisters.eIR) & 0x0000FF00;
			operand2 = operand2 >> 8;
			move(ERegisters.values()[operand1], ERegisters.values()[operand2]);

		} else if (opCode == EOpcode.eAdd.ordinal()) {
			
		}
	}
	private void excute() {
		
	}
	
	public void run() {
		this.fetch();
		this.decode();
		this.excute();
	}
	
	public void finish() {
		
	}	
}
