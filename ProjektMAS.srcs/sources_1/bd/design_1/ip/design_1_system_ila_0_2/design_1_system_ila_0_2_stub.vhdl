-- Copyright 1986-2017 Xilinx, Inc. All Rights Reserved.
-- --------------------------------------------------------------------------------
-- Tool Version: Vivado v.2017.4 (win64) Build 2086221 Fri Dec 15 20:55:39 MST 2017
-- Date        : Wed Jan 16 21:13:46 2019
-- Host        : Karlo-Laptop running 64-bit major release  (build 9200)
-- Command     : write_vhdl -force -mode synth_stub
--               d:/MAS/ProjektMAS/ProjektMAS.srcs/sources_1/bd/design_1/ip/design_1_system_ila_0_2/design_1_system_ila_0_2_stub.vhdl
-- Design      : design_1_system_ila_0_2
-- Purpose     : Stub declaration of top-level module interface
-- Device      : xc7z020clg400-1
-- --------------------------------------------------------------------------------
library IEEE;
use IEEE.STD_LOGIC_1164.ALL;

entity design_1_system_ila_0_2 is
  Port ( 
    clk : in STD_LOGIC;
    SLOT_0_IIC_scl_i : in STD_LOGIC;
    SLOT_0_IIC_scl_o : in STD_LOGIC;
    SLOT_0_IIC_scl_t : in STD_LOGIC;
    SLOT_0_IIC_sda_i : in STD_LOGIC;
    SLOT_0_IIC_sda_o : in STD_LOGIC;
    SLOT_0_IIC_sda_t : in STD_LOGIC
  );

end design_1_system_ila_0_2;

architecture stub of design_1_system_ila_0_2 is
attribute syn_black_box : boolean;
attribute black_box_pad_pin : string;
attribute syn_black_box of stub : architecture is true;
attribute black_box_pad_pin of stub : architecture is "clk,SLOT_0_IIC_scl_i,SLOT_0_IIC_scl_o,SLOT_0_IIC_scl_t,SLOT_0_IIC_sda_i,SLOT_0_IIC_sda_o,SLOT_0_IIC_sda_t";
attribute X_CORE_INFO : string;
attribute X_CORE_INFO of stub : architecture is "bd_378d,Vivado 2017.4";
begin
end;
