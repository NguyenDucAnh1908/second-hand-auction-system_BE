package com.second_hand_auction_system.service.bid;

import com.second_hand_auction_system.dtos.request.bid.BidDto;
import com.second_hand_auction_system.dtos.responses.bid.BidResponse;

import java.util.List;

public interface IBidService {

    BidResponse createBid(BidDto bidDto) throws Exception;

    BidResponse updateBid(Integer bidId, BidDto bidDto) throws Exception;

    void deleteBid(Integer bidId) throws Exception;

    BidResponse getBidById(Integer bidId) throws Exception;

    List<BidResponse> getAllBidsByAuctionId(Integer auctionId) throws Exception;
}