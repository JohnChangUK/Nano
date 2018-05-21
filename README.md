# Nano Java API

Java API for the Nano RPC protocol 

## Usage

```java
// defaults to http://localhost:7076
NanoClient client = new NanoClient();

// or specify the host
NanoClient client = new NanoClient("http://123.45.67.8:7076");

// Check account balance
AccountBalance balance = client.getAccountBalance("xrb_3t6k35gi95xu6tergt6p69ck76ogmitsa8mnijtpxm9fkcm736xtoncuohr3");
```

## Implemented methods

- [x] Accounts
  - [x] Account balance
  - [x] Account block count
  - [x] Account create
  - [x] Account get
  - [x] Account history
  - [x] Account information
  - [x] Account list
  - [x] Account move
  - [x] Account public key
  - [x] Account remove
  - [x] Account representative
  - [x] Account representative set
  - [x] Account weight
  - [x] Accounts balances
  - [x] Accounts create
  - [x] Accounts frontiers
  - [x] Accounts pending
  - [x] Validate account number checksum
- [ ] Blocks
  - [x] Block account
  - [x] Block count by type
  - [x] Block count
  - [x] Chain
  - [ ] Offline signing (create block)
  - [ ] Process block
  - [ ] Retrieve block
  - [ ] Retrieve multiple blocks with additional info
  - [ ] Retrieve multiple blocks
- [ ] Bootstrap
  - [ ] Bootstrap
  - [ ] Multi-connection bootstrap
- [x] Conversion
  - [x] Krai from raw
  - [x] Krai to raw
  - [x] Mrai from raw
  - [x] Mrai to raw
  - [x] Rai from raw
  - [x] Rai to raw
- [ ] Delegators
  - [ ] Delegators
  - [ ] Delegators count
- [ ] Frontiers
  - [ ] Frontiers
  - [ ] Frontier count
- [ ] Keys
  - [ ] Deterministic key
  - [ ] Key create
  - [ ] Key expand
- [ ] Ledger
  - [ ] History
  - [ ] Ledger
  - [ ] Successors
- [ ] Network
  - [ ] Available supply
  - [ ] Keepalive
  - [ ] Republish
- [x] Node
  - [x] Retrieve node versions
  - [x] Stop node
- [ ] Payments
  - [ ] Payment begin
  - [ ] Payment end
  - [ ] Payment init
  - [ ] Payment wait
- [ ] Peers
  - [ ] Add work peer
  - [ ] Clear work peers
  - [ ] Retrieve online peers
  - [ ] Retrieve work peers
- [ ] Pending
  - [ ] Pending
  - [ ] Pending exists
  - [ ] Search pending
  - [ ] Search pending for all wallets
- [ ] Proof of Work
  - [ ] Work cancel
  - [ ] Work generate
  - [ ] Work get
  - [ ] Work set
  - [ ] Work validate
- [x] Receiving
  - [x] Receive
  - [x] Receive minimum
  - [x] Receive minimum set
- [x] Representatives
  - [x] Representatives
  - [x] Wallet representative
  - [x] Wallet representative set
- [x] Sending
  - [x] Send
- [ ] Unchecked blocks
  - [ ] Clear unchecked blocks
  - [ ] Retrieve unchecked block
  - [ ] Unchecked blocks with database keys
  - [ ] Unchecked blocks
- [ ] Wallet
  - [x] Wallet accounts balances
  - [ ] Wallet add key
  - [ ] Wallet change password
  - [x] Wallet change seed
  - [x] Wallet contains
  - [ ] Wallet create
  - [ ] Wallet destroy
  - [ ] Wallet export
  - [ ] Wallet frontiers
  - [ ] Wallet locked check
  - [ ] Wallet password enter (unlock wallet)
  - [ ] Wallet pending
  - [x] Wallet representative
  - [x] Wallet representative set
  - [ ] Wallet republish
  - [ ] Wallet total balance
  - [ ] Wallet valid password
  - [ ] Wallet work get
- [ ] RPC callback

## Credits
Credit for base implementation goes to [caseyscarborough](https://github.com/caseyscarborough/nano-java)