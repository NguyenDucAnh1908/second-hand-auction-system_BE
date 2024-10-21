package com.second_hand_auction_system.service.bid;

import com.second_hand_auction_system.converters.bid.BidConverter;
import com.second_hand_auction_system.dtos.request.bid.BidDto;
import com.second_hand_auction_system.dtos.responses.bid.BidResponse;
import com.second_hand_auction_system.models.Auction;
import com.second_hand_auction_system.models.Bid;
import com.second_hand_auction_system.models.User;
import com.second_hand_auction_system.repositories.AuctionRepository;
import com.second_hand_auction_system.repositories.BidRepository;
import com.second_hand_auction_system.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BidService implements IBidService{
    private final BidRepository bidRepository;

    private final UserRepository userRepository;

    private final AuctionRepository auctionRepository;

    @Override
    public BidResponse createBid(BidDto bidDto) throws Exception {
        // Lấy đối tượng User và Auction từ repository
        User user = userRepository.findById(bidDto.getUserId())
                .orElseThrow(() -> new Exception("User not found"));
        Auction auction = auctionRepository.findById(bidDto.getAuctionId())
                .orElseThrow(() -> new Exception("Auction not found"));

        // Chuyển đổi từ BidDto sang Bid bằng BidConverter
        Bid bid = BidConverter.convertToEntity(bidDto, user, auction);

        // Lưu Bid vào database
        Bid savedBid = bidRepository.save(bid);

        // Chuyển đổi từ Bid sang BidResponses bằng BidConverter và trả về
        return BidConverter.convertToResponse(savedBid);
    }

    @Override
    public BidResponse updateBid(Integer bidId, BidDto bidDto) throws Exception {
        // Find existing bid
        Bid existingBid = bidRepository.findById(bidId)
                .orElseThrow(() -> new Exception("Bid not found"));

        // Find User and Auction entities
        User user = userRepository.findById(bidDto.getUserId())
                .orElseThrow(() -> new Exception("User not found"));
        Auction auction = auctionRepository.findById(bidDto.getAuctionId())
                .orElseThrow(() -> new Exception("Auction not found"));

        // Update bid details
        existingBid.setBidAmount(bidDto.getBidAmount());
        existingBid.setBidTime(bidDto.getBidTime());
        existingBid.setBidStatus(bidDto.getBidStatus());
        existingBid.setWinBid(bidDto.isWinBid());
        existingBid.setUser(user);
        existingBid.setAuction(auction);

        // Save updated bid
        Bid updatedBid = bidRepository.save(existingBid);

        // Convert entity to response
        return BidConverter.convertToResponse(updatedBid);
    }

    @Override
    public void deleteBid(Integer bidId) throws Exception {
        // Find bid
        Bid bid = bidRepository.findById(bidId)
                .orElseThrow(() -> new Exception("Bid not found"));

        // Delete bid
        bidRepository.delete(bid);
    }

    @Override
    public BidResponse getBidById(Integer bidId) throws Exception {
        // Find bid
        Bid bid = bidRepository.findById(bidId)
                .orElseThrow(() -> new Exception("Bid not found"));

        // Convert entity to response
        return BidConverter.convertToResponse(bid);
    }

    @Override
    public List<BidResponse> getAllBidsByAuctionId(Integer auctionId) throws Exception {
        // Find all bids by auction
        List<Bid> bids = bidRepository.findByAuction_AuctionId(auctionId);

        // Convert list of entities to list of responses
        return bids.stream()
                .map(BidConverter::convertToResponse)
                .collect(Collectors.toList());
    }
}