//Copyright 1986-2017 Xilinx, Inc. All Rights Reserved.
//--------------------------------------------------------------------------------
//Command: generate_target bd_365d.bd
//Design : bd_365d
//Purpose: IP block netlist
//--------------------------------------------------------------------------------
`timescale 1 ps / 1 ps

(* CORE_GENERATION_INFO = "bd_365d,IP_Integrator,{x_ipVendor=xilinx.com,x_ipLibrary=BlockDiagram,x_ipName=bd_365d,x_ipVersion=1.00.a,x_ipLanguage=VERILOG,numBlks=1,numReposBlks=1,numNonXlnxBlks=0,numHierBlks=0,maxHierDepth=0,numSysgenBlks=0,numHlsBlks=0,numHdlrefBlks=0,numPkgbdBlks=0,bdsource=SBD,synth_mode=Global}" *) (* HW_HANDOFF = "design_1_system_ila_1_0.hwdef" *) 
module bd_365d
   (SLOT_0_GPIO_tri_i,
    clk);
  (* X_INTERFACE_INFO = "xilinx.com:interface:gpio:1.0 SLOT_0_GPIO TRI_I" *) input [10:0]SLOT_0_GPIO_tri_i;
  (* X_INTERFACE_INFO = "xilinx.com:signal:clock:1.0 CLK.CLK CLK" *) (* X_INTERFACE_PARAMETER = "XIL_INTERFACENAME CLK.CLK, CLK_DOMAIN design_1_processing_system7_0_0_FCLK_CLK0, FREQ_HZ 100000000, PHASE 0.000" *) input clk;

  wire [10:0]SLOT_0_GPIO_tri_i_1;
  wire clk_1;

  assign SLOT_0_GPIO_tri_i_1 = SLOT_0_GPIO_tri_i[10:0];
  assign clk_1 = clk;
  bd_365d_ila_lib_0 ila_lib
       (.clk(clk_1),
        .probe0(SLOT_0_GPIO_tri_i_1));
endmodule
