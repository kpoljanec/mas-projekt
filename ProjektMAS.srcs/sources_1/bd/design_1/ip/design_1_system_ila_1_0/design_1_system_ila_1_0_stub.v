// Copyright 1986-2017 Xilinx, Inc. All Rights Reserved.
// --------------------------------------------------------------------------------
// Tool Version: Vivado v.2017.4 (win64) Build 2086221 Fri Dec 15 20:55:39 MST 2017
// Date        : Wed Jan 16 22:19:04 2019
// Host        : Karlo-Laptop running 64-bit major release  (build 9200)
// Command     : write_verilog -force -mode synth_stub
//               d:/MAS/ProjektMAS/ProjektMAS.srcs/sources_1/bd/design_1/ip/design_1_system_ila_1_0/design_1_system_ila_1_0_stub.v
// Design      : design_1_system_ila_1_0
// Purpose     : Stub declaration of top-level module interface
// Device      : xc7z020clg400-1
// --------------------------------------------------------------------------------

// This empty module with port declaration file causes synthesis tools to infer a black box for IP.
// The synthesis directives are for Synopsys Synplify support to prevent IO buffer insertion.
// Please paste the declaration into a Verilog source file or add the file as an additional source.
(* X_CORE_INFO = "bd_365d,Vivado 2017.4" *)
module design_1_system_ila_1_0(clk, SLOT_0_GPIO_tri_i)
/* synthesis syn_black_box black_box_pad_pin="clk,SLOT_0_GPIO_tri_i[10:0]" */;
  input clk;
  input [10:0]SLOT_0_GPIO_tri_i;
endmodule
