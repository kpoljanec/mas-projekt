// Copyright 1986-2017 Xilinx, Inc. All Rights Reserved.
// --------------------------------------------------------------------------------
// Tool Version: Vivado v.2017.4 (win64) Build 2086221 Fri Dec 15 20:55:39 MST 2017
// Date        : Wed Jan 16 21:13:45 2019
// Host        : Karlo-Laptop running 64-bit major release  (build 9200)
// Command     : write_verilog -force -mode synth_stub
//               d:/MAS/ProjektMAS/ProjektMAS.srcs/sources_1/bd/design_1/ip/design_1_system_ila_0_2/design_1_system_ila_0_2_stub.v
// Design      : design_1_system_ila_0_2
// Purpose     : Stub declaration of top-level module interface
// Device      : xc7z020clg400-1
// --------------------------------------------------------------------------------

// This empty module with port declaration file causes synthesis tools to infer a black box for IP.
// The synthesis directives are for Synopsys Synplify support to prevent IO buffer insertion.
// Please paste the declaration into a Verilog source file or add the file as an additional source.
(* X_CORE_INFO = "bd_378d,Vivado 2017.4" *)
module design_1_system_ila_0_2(clk, SLOT_0_IIC_scl_i, SLOT_0_IIC_scl_o, 
  SLOT_0_IIC_scl_t, SLOT_0_IIC_sda_i, SLOT_0_IIC_sda_o, SLOT_0_IIC_sda_t)
/* synthesis syn_black_box black_box_pad_pin="clk,SLOT_0_IIC_scl_i,SLOT_0_IIC_scl_o,SLOT_0_IIC_scl_t,SLOT_0_IIC_sda_i,SLOT_0_IIC_sda_o,SLOT_0_IIC_sda_t" */;
  input clk;
  input SLOT_0_IIC_scl_i;
  input SLOT_0_IIC_scl_o;
  input SLOT_0_IIC_scl_t;
  input SLOT_0_IIC_sda_i;
  input SLOT_0_IIC_sda_o;
  input SLOT_0_IIC_sda_t;
endmodule
