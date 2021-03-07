package com.bbdn.server.domain.interfaces.response;

import com.bbdn.server.domain.interfaces.vo.AddressLocationVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import sun.jvm.hotspot.debugger.Address;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class LocationLineByAddressNameResponse {
    String addressName;
    AddressLocationVO startLocation;
    AddressLocationVO endLocation;
}
