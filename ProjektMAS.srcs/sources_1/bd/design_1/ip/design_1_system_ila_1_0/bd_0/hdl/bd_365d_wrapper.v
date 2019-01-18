//Copyright 1986-2017 Xilinx, Inc. All Rights Reserved.
//--------------------------------------------------------------------------------
//Command: generate_target bd_365d_wrapper.bd
//Design : bd_365d_wrapper
//Purpose: IP block netlist
//--------------------------------------------------------------------------------
`timescale 1 ps / 1 ps

module bd_365d_wrapper
   (SLOT_0_GPIO_tri_i,
    clk);
  input [10:0]SLOT_0_GPIO_tri_i;
  input clk;

  wire [10:0]SLOT_0_GPIO_tri_i;
  wire clk;

  bd_365d bd_365d_i
       (.SLOT_0_GPIO_tri_i(SLOT_0_GPIO_tri_i),
        .clk(clk));
endmodule
