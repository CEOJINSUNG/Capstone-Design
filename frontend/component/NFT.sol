pragma solidity 0.6.6;
import "https://github.com/OpenZeppelin/openzeppelin-contracts/blob/v3.4.0/contracts/presets/ERC721PresetMinterPauserAutoId.sol";

contract SimpleCollectible is ERC721 {
  uint256 public tokenCounter;
  constructor () public ERC721 ("Tottenham", "Token"){
    tokenCounter = 0;
  }
  function createCollectible(string memory tokenURI) public returns (uint256) {
    uint256 newItemId = tokenCounter;
    _safeMint(msg.sender, newItemId);
    _setTokenURI(newItemId, tokenURI);
    tokenCounter = tokenCounter + 1;
    return newItemId;
  }
}