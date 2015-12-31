package info.pinlab.snd.dsp;

/**
 * 
 * In-place windower.
 * The FrameProcessor method: {@link #process(double[])} returns null.
 *
 *<pre>
 * predecessorKey : "sample"
 * key            : "sample"
 * </pre>
 * 
 * @author Gabor Pinter
 */
public class HanningWindower extends AbstractFrameProcessor implements Windower{
//	"info.pinlab.snd.dsp.HanningWindower.frameLen"
	
	
	static double [] HANN_160 = new double [160];
	static double [] HANN_320 = new double [320];
	static{
		HANN_160[0] = 0.00000000000000000000e+00; HANN_160[1] = 3.90345323214080774932e-04;
		HANN_160[2] = 1.56077181497088490758e-03; HANN_160[3] = 3.50945199324154133436e-03;
		HANN_160[4] = 6.23334322524987660685e-03; HANN_160[5] = 9.72819247818251797710e-03;
		HANN_160[6] = 1.39885429597946253644e-02; HANN_160[7] = 1.90077426385431014211e-02;
		HANN_160[8] = 2.47779546299444763946e-02; HANN_160[9] = 3.12901694329395518857e-02;
		HANN_160[10] = 3.85342189971599191622e-02;HANN_160[11] = 4.64987925991313666430e-02;
		HANN_160[12] = 5.51714545026263381011e-02;HANN_160[13] = 6.45386633755893890374e-02;
		HANN_160[14] = 7.45857934333200023325e-02;HANN_160[15] = 8.52971572748991158086e-02;
		HANN_160[16] = 9.66560303772034945524e-02;HANN_160[17] = 1.08644677208263373824e-01;
		HANN_160[18] = 1.21244378919189932375e-01;HANN_160[19] = 1.34435462571436292745e-01;
		HANN_160[20] = 1.48197331853755276398e-01;HANN_160[21] = 1.62508499240894721538e-01;
		HANN_160[22] = 1.77346619543817418663e-01;HANN_160[23] = 1.92688524799061289272e-01;
		HANN_160[24] = 2.08510260442763939448e-01;HANN_160[25] = 2.24787122712870879315e-01;
		HANN_160[26] = 2.41493697221127012753e-01;HANN_160[27] = 2.58603898634627571429e-01;
		HANN_160[28] = 2.76091011404968500464e-01;HANN_160[29] = 2.93927731481404053149e-01;
		HANN_160[30] = 3.12086208942880860562e-01;HANN_160[31] = 3.30538091482384110975e-01;
		HANN_160[32] = 3.49254568675698484626e-01;HANN_160[33] = 3.68206416965466187818e-01;
		HANN_160[34] = 3.87364045290302549951e-01;HANN_160[35] = 4.06697541287725450321e-01;
		HANN_160[36] = 4.26176717998759335693e-01;HANN_160[37] = 4.45771161001287219428e-01;
		HANN_160[38] = 4.65450275898560916765e-01;HANN_160[39] = 4.85183336088720051915e-01;
		HANN_160[40] = 5.04939530740732833713e-01;HANN_160[41] = 5.24688012901851408287e-01;
		HANN_160[42] = 5.44397947661467429548e-01;HANN_160[43] = 5.64038560296163393737e-01;
		HANN_160[44] = 5.83579184320791588547e-01;HANN_160[45] = 6.02989309370549175782e-01;
		HANN_160[46] = 6.22238628839291263439e-01;HANN_160[47] = 6.41297087199699467064e-01;
		HANN_160[48] = 6.60134926931418841711e-01;HANN_160[49] = 6.78722734983894460115e-01;
		HANN_160[50] = 6.97031488701358448168e-01;HANN_160[51] = 7.15032601138260059948e-01;
		HANN_160[52] = 7.32697965694390607183e-01;HANN_160[53] = 7.49999999999999888978e-01;
		HANN_160[54] = 7.66911688982395367553e-01;HANN_160[55] = 7.83406627046768777589e-01;
		HANN_160[56] = 7.99459059305400177031e-01;HANN_160[57] = 8.15043921790855496745e-01;
		HANN_160[58] = 8.30136880590398695823e-01;HANN_160[59] = 8.44714369840505963083e-01;
		HANN_160[60] = 8.58753628522165524117e-01;HANN_160[61] = 8.72232735999506347824e-01;
		HANN_160[62] = 8.85130646246268693034e-01;HANN_160[63] = 8.97427220706676687101e-01;
		HANN_160[64] = 9.09103259739401203809e-01;HANN_160[65] = 9.20140532595522087078e-01;
		HANN_160[66] = 9.30521805883677721738e-01;HANN_160[67] = 9.40230870477959834730e-01;
		HANN_160[68] = 9.49252566826539911915e-01;HANN_160[69] = 9.57572808621509174998e-01;
		HANN_160[70] = 9.65178604792977234972e-01;HANN_160[71] = 9.72058079793084561793e-01;
		HANN_160[72] = 9.78200492138261212816e-01;HANN_160[73] = 9.83596251180778535783e-01;
		HANN_160[74] = 9.88236932083406793836e-01;HANN_160[75] = 9.92115288973798414851e-01;
		HANN_160[76] = 9.95225266258057739144e-01;HANN_160[77] = 9.97562008075832062914e-01;
		HANN_160[78] = 9.99121865882160675731e-01;HANN_160[79] = 9.99902404144245471329e-01;
		HANN_160[80] = 9.99902404144245471329e-01;HANN_160[81] = 9.99121865882160786754e-01;
		HANN_160[82] = 9.97562008075832062914e-01;
		HANN_160[83] = 9.95225266258057850166e-01;
		HANN_160[84] = 9.92115288973798414851e-01;
		HANN_160[85] = 9.88236932083406793836e-01;
		HANN_160[86] = 9.83596251180778535783e-01;
		HANN_160[87] = 9.78200492138261212816e-01;
		HANN_160[88] = 9.72058079793084561793e-01;
		HANN_160[89] = 9.65178604792977345994e-01;
		HANN_160[90] = 9.57572808621509286020e-01;
		HANN_160[91] = 9.49252566826539911915e-01;
		HANN_160[92] = 9.40230870477959834730e-01;
		HANN_160[93] = 9.30521805883677832760e-01;
		HANN_160[94] = 9.20140532595522087078e-01;
		HANN_160[95] = 9.09103259739401092787e-01;
		HANN_160[96] = 8.97427220706676687101e-01;
		HANN_160[97] = 8.85130646246268804056e-01;
		HANN_160[98] = 8.72232735999506458846e-01;
		HANN_160[99] = 8.58753628522165524117e-01;
		HANN_160[100] = 8.44714369840505963083e-01;
		HANN_160[101] = 8.30136880590398584800e-01;
		HANN_160[102] = 8.15043921790855829812e-01;
		HANN_160[103] = 7.99459059305400510098e-01;
		HANN_160[104] = 7.83406627046768999634e-01;
		HANN_160[105] = 7.66911688982395478575e-01;
		HANN_160[106] = 7.50000000000000222045e-01;
		HANN_160[107] = 7.32697965694390829228e-01;
		HANN_160[108] = 7.15032601138260170970e-01;
		HANN_160[109] = 6.97031488701358448168e-01;
		HANN_160[110] = 6.78722734983894682159e-01;
		HANN_160[111] = 6.60134926931419063756e-01;
		HANN_160[112] = 6.41297087199699356042e-01;
		HANN_160[113] = 6.22238628839291374462e-01;
		HANN_160[114] = 6.02989309370549286804e-01;
		HANN_160[115] = 5.83579184320791810592e-01;
		HANN_160[116] = 5.64038560296163282715e-01;
		HANN_160[117] = 5.44397947661467318525e-01;
		HANN_160[118] = 5.24688012901851519310e-01;
		HANN_160[119] = 5.04939530740732944736e-01;
		HANN_160[120] = 4.85183336088720273960e-01;
		HANN_160[121] = 4.65450275898560805743e-01;
		HANN_160[122] = 4.45771161001287163916e-01;
		HANN_160[123] = 4.26176717998759335693e-01;
		HANN_160[124] = 4.06697541287725616854e-01;
		HANN_160[125] = 3.87364045290302660973e-01;
		HANN_160[126] = 3.68206416965465965774e-01;
		HANN_160[127] = 3.49254568675698373603e-01;
		HANN_160[128] = 3.30538091482384110975e-01;
		HANN_160[129] = 3.12086208942880971584e-01;
		HANN_160[130] = 2.93927731481404164171e-01;
		HANN_160[131] = 2.76091011404968278420e-01;
		HANN_160[132] = 2.58603898634627515918e-01;
		HANN_160[133] = 2.41493697221127012753e-01;
		HANN_160[134] = 2.24787122712870934826e-01;
		HANN_160[135] = 2.08510260442764105981e-01;
		HANN_160[136] = 1.92688524799061178250e-01;
		HANN_160[137] = 1.77346619543817363152e-01;
		HANN_160[138] = 1.62508499240894721538e-01;
		HANN_160[139] = 1.48197331853755331910e-01;
		HANN_160[140] = 1.34435462571436459278e-01;
		HANN_160[141] = 1.21244378919190154420e-01;
		HANN_160[142] = 1.08644677208263318313e-01;
		HANN_160[143] = 9.66560303772034945524e-02;
		HANN_160[144] = 8.52971572748991713198e-02;
		HANN_160[145] = 7.45857934333201133548e-02;
		HANN_160[146] = 6.45386633755895555709e-02;
		HANN_160[147] = 5.51714545026263381011e-02;
		HANN_160[148] = 4.64987925991314221541e-02;
		HANN_160[149] = 3.85342189971599191622e-02;
		HANN_160[150] = 3.12901694329396073968e-02;
		HANN_160[151] = 2.47779546299445319057e-02;
		HANN_160[152] = 1.90077426385432124434e-02;
		HANN_160[153] = 1.39885429597946253644e-02;
		HANN_160[154] = 9.72819247818257348825e-03;
		HANN_160[155] = 6.23334322524993211800e-03;
		HANN_160[156] = 3.50945199324154133436e-03;
		HANN_160[157] = 1.56077181497088490758e-03;
		HANN_160[158] = 3.90345323214080774932e-04;
		HANN_160[159] = 0.00000000000000000000e+00;
	}
	
	double [] filter;

	
	public HanningWindower(ParameterSheet context){
		super(context);
	}
	
	@Override
	public String getPredecessorKey() {
		return "preEmph";
	}

	@Override
	public String getKey() {
		return "win";
	}
	
	
	@Override
	public void init(){
		super.setKey("win");
		super.setPredecessorKey("preEmph");
		int size = context.get(FEParam.FRAME_LEN_SAMPLE);
		filter = new double[size];
		if(size==160){
			for(int i = 0; i < size ; i++){
				filter[i] = HANN_160[i];
			}
		}else{
			for(int i = 0; i < size ; i++){
				filter[i] = (0.5 - (0.5 * Math.cos(2 * Math.PI * i / (size - 1) )));
			}
		}		
	}

	@Override
	public double[] process(double[] arr) {
//		System.out.println(arr.length +" == " + filter.length);
		for(int i = 0; i < filter.length ; i++){
			arr[i] *= filter[i];
		}
		return null;
	}

	@Override
	public void filter(double[] samples) {
		process(samples);
	}
	
	
	@Override
	public WindowType getWindowType() {
		return Windower.WindowType.HANNING;
	}

	
	
	
}
